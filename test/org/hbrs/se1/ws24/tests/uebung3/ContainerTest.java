package org.hbrs.se1.ws24.tests.uebung3;

import org.hbrs.se1.ws24.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.Container;
import org.hbrs.se1.ws24.exercises.uebung2.Member;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

  private Container toTest;
  private Member m1,m2;

  @BeforeEach
  public void setUp() {
    toTest = Container.getInstance();
    toTest.setPersistenceStrategy(null);
    m1 = new ConcreteMember(1);
    m2 = new ConcreteMember(2);
  }
  @Test
  public void persistenceStrategyNullTest() {
    assertThrowsExactly(PersistenceException.class, () -> toTest.store());
    assertThrowsExactly(PersistenceException.class, () -> toTest.load());
  }

  @Test
  public void persistenceStrategyMongoDBTest() {
    assertThrowsExactly(UnsupportedOperationException.class, () -> toTest.setPersistenceStrategy(new PersistenceStrategyMongoDB<Member>()));
  }

  @Test
  public void setDirectoryAsPathTest() {
    PersistenceStrategyStream<Member> persistenceStrategy = new PersistenceStrategyStream<>();
    persistenceStrategy.setLocation("src");
    toTest.setPersistenceStrategy(persistenceStrategy);
    assertThrowsExactly(PersistenceException.class , () -> toTest.store());
    assertThrowsExactly(PersistenceException.class, () -> toTest.load());
  }

  @Test
  public void roundTripTest() {
    PersistenceStrategyStream<Member> persistenceStrategy = new PersistenceStrategyStream<>();
    toTest.setPersistenceStrategy(persistenceStrategy);
    assertTrue(toTest.getCurrentList().isEmpty());
    toTest.addMember(m1);
    assertEquals(1, toTest.size());
    assertTrue(toTest.getCurrentList().contains(m1));
    try {
      toTest.store();
    } catch (PersistenceException e) {
      throw new RuntimeException(e);
    }
    assertEquals("Folgender Member wurde gelöscht: 1",toTest.deleteMember(1));
    assertTrue(toTest.getCurrentList().isEmpty());
    try {
      toTest.load();
    } catch (PersistenceException e) {
      throw new RuntimeException(e);
    }
    assertTrue(toTest.getCurrentList().contains(m1));
    assertEquals(1, toTest.size());
    toTest.addMember(m2);
    assertEquals(2, toTest.size());
    assertTrue(toTest.getCurrentList().contains(m1));
    assertTrue(toTest.getCurrentList().contains(m2));
    try {
      toTest.store();
    } catch (PersistenceException e) {
      throw new RuntimeException(e);
    }
    assertEquals("Folgender Member wurde gelöscht: 1",toTest.deleteMember(1));
    assertEquals("Folgender Member wurde gelöscht: 2",toTest.deleteMember(2));
    try {
      toTest.load();
    } catch (PersistenceException e) {
      throw new RuntimeException(e);
    }
    assertEquals(2, toTest.size());
    assertTrue(toTest.getCurrentList().contains(m1));
    assertTrue(toTest.getCurrentList().contains(m2));
  }

  @AfterEach
  public void tearDown() {
    toTest = null;
    m1=null;
    m2=null;
  }


}
