package org.hbrs.se1.ws24.tests.uebung4;

import org.hbrs.se1.ws24.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws24.exercises.uebung4.Container;
import org.hbrs.se1.ws24.exercises.uebung4.UserStory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

  private Container<String> testContainer = null;
  private static PersistenceStrategy<String> persistenceStrategy = null;

  /**
   * Initialisierung der Testumgebung damit <code>objects.ser</code> nicht überschrieben wird.
   */
  @BeforeAll
  public static void setUp() {
    PersistenceStrategyStream<String> persistenceStrategy = new PersistenceStrategyStream();
    persistenceStrategy.setLocation("test.objects.ser");
    ContainerTest.persistenceStrategy = persistenceStrategy;
  }


  /**
   * Container wird bei jedem Test zurückgesetzt und damit immer neu initialisiert.
   */
  @BeforeEach
  public void setUpTest() {
    Container.reset();
    testContainer = Container.getInstance();
    testContainer.setPersistenceStrategy(persistenceStrategy);
  }

  /**
   * Prüft, ob das Singleton-Objekt korrekt erstellt wird.
   */
  @Test
  public void getInstanceTest() {
    Container<UserStory> container2 = Container.getInstance();
    assertEquals(testContainer, container2);
    UserStory story = new UserStory("test", "test", "test", 1, 1, 1, 1);
    container2.addItem(story);
    assertTrue(testContainer.getCurrentList().contains(story));
    testContainer.addItem("test");
    assertTrue(container2.getCurrentList().contains("test"));
  }

  /**
   * Prüft, ob die Methode <code>setPersistenceStrategy</code> eine <code>UnsupportedOperationException</code>
   * wirft, wenn eine nicht implementierte <code>PersistenceStrategy</code> verwendet wird.
   */
  @Test
  public void setPersistenceStrategyTest() {
    assertThrowsExactly(UnsupportedOperationException.class,
        () -> testContainer.setPersistenceStrategy(new PersistenceStrategyMongoDB()));

    //Test für ein nicht implementierter PersistenceStrategy
    assertThrowsExactly(UnsupportedOperationException.class,() -> Container.getInstance().setPersistenceStrategy(new PersistenceStrategy<Object>() {
      @Override
      public void save(List<Object> member) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not implemented!");
      }

      @Override
      public List<Object> load() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not implemented!");
      }
    }));
  }

  /**
   * Prüft, ob die Methoden <code>store</code> und <code>load</code> eine <code>PersistenceException</code>
   * wirft, wenn keine <code>PersistenceStrategy</code> gesetzt ist.
   */
  @Test
  public void nullPersistenceStrategyTest() {
    Container.reset();
    testContainer = Container.getInstance();
    assertThrowsExactly(PersistenceException.class, () -> testContainer.store());
    assertThrowsExactly(PersistenceException.class, () -> testContainer.load());
  }

  /**
   * Dies ist ein Roundtrip-Test, der prüft, ob die Methoden <code>addItem</code>, <code>store</code>
   * und <code>load</code> einen korrekten Funktionsverlauf erzeugen.
   */
  @Test
  public void roundTripTest() {
    assertEquals(0, testContainer.size());
    assertTrue(testContainer.getCurrentList().isEmpty());
    testContainer.addItem("iti");
    assertTrue(testContainer.getCurrentList().contains("iti"));
    assertEquals(1, testContainer.size());
    assertEquals(1,testContainer.getCurrentList().size());
    assertTrue(testContainer.getCurrentList().contains("iti"));
    assertDoesNotThrow(() -> testContainer.store());
    assertThrowsExactly(ContainerException.class, () -> testContainer.addItem("iti"));
    assertEquals(1, testContainer.size());
    assertEquals(1,testContainer.getCurrentList().size());
    assertTrue(testContainer.getCurrentList().contains("iti"));
    testContainer.addItem("ni");
    assertEquals(2, testContainer.size());
    assertEquals(2,testContainer.getCurrentList().size());
    assertTrue(testContainer.getCurrentList().contains("iti"));
    assertTrue(testContainer.getCurrentList().contains("ni"));
    assertDoesNotThrow(()-> testContainer.load());
    assertEquals(1, testContainer.size());
    assertEquals(1,testContainer.getCurrentList().size());
    assertTrue(testContainer.getCurrentList().contains("iti"));
    assertFalse(testContainer.getCurrentList().contains("ni"));
  }

  /**
   * Hier wird die von der <code>PersistenceStrategy</code> verwendete <code>test.objects.ser</code> gelöscht.
   */
  @AfterAll
  public static void tearDown() {
    new File("test.objects.ser").deleteOnExit();
  }

}
