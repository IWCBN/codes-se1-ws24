package org.hbrs.se1.ws24.tests.uebung4;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws24.exercises.uebung4.CommandHandler;
import org.hbrs.se1.ws24.exercises.uebung4.Container;
import org.hbrs.se1.ws24.exercises.uebung4.UserStory;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class CommandHandlerTest {

  private static final InputStream originalInput = System.in;
  private static final PrintStream originalOut = System.out;
  private Container container;
  private ByteArrayOutputStream out = null;
  private PersistenceStrategy<UserStory> persistenceStrategy = new PersistenceStrategyStream<>();

  /**
   * Setup für die Tests, um den Output Stream und den Container vorzubereiten.
   */
  @BeforeEach
  public void setUp() {
    container = Container.getInstance();
    container.setPersistenceStrategy(persistenceStrategy);
    out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
  }

  /**
   * Test auf fehlerhaften Command.
   */
  @Test
  public void notExistingCommandTest() {
    System.setIn(new ByteArrayInputStream("notExistingCommand\nexit\n".getBytes()));
    assertDoesNotThrow(() -> CommandHandler.run(container,new Scanner(System.in)));
    assertEquals("Falsche Eingabe. Versuche 'help' für mehr Informationen.\n", out.toString());
  }

  /**
   * Test für den Help Command
   */
  @Test
  public void helpTest() {
    System.setIn(new ByteArrayInputStream("help\nexit\n".getBytes()));
    assertDoesNotThrow(() -> CommandHandler.run(container,new Scanner(System.in)));
    assertEquals("Folgende Befehle stehen zur Verfügung:\n" +
        "exit: Beendet das Programm.\n" +
        "help: Gibt genau diese Liste aus.\n" +
        "enter [\"Titel\" \"Akzeptanzkriterium\" \"Mehrwert\" \"Strafe\" \"Aufwand\" \"Risiko\" \"Projekt Name\"]:\n" +
        "\tLegt eine neue User Story in dem System an. Folgende Werte werden benötigt:\n" +
        "\t\tTitel: Titel der User Story\n" +
        "\t\tAkzeptanzkriterium: Beschreibt Testfälle mit denen das erfolgreiche Abschließen einer User Story erreicht werden kann.\n" +
        "\t\tMehrwert: Ist eine Zahl, die den Mehrwert für das Unternehmen beziffert.\n" +
        "\t\tStrafe: Ist eine Zahl, die die Höhe der Strafe beziffert.\n" +
        "\t\tAufwand: Ist eine Zahl, die den Aufwand beziffert um die User Story umzusetzen.\n" +
        "\t\tRisiko: Ist eine Zahl, die das Risiko beziffert wenn die User Story nicht umgesetzt wird.\n" +
        "\t\tProjekt Name: Gibt den Namen des zugehörigen Projektes aus.\n" +
        "store: Speichert den aktellen Stand der User Stories\n" +
        "load: Lädt den aktuellen Stand der User Stories von der Festplatte\n", out.toString());
  }

  /**
   * Test für den Enter Command
   */
  @Test
  public void enterTest() {
    //Eingabe simulation vorbereiten.
    String enter = "enter \"1\" \"2\" \"3\" \"4\" \"5\" \"6\" \"7\"\n";
    enter += "enter wrong\n";
    enter += "Titel\n";
    enter += "Kriterium\n";
    enter += "1\n";
    enter += "2\n";
    enter += "3\n";
    enter += "4\n";
    enter += "Projekt\n";
    enter += "exit\n";
    System.setIn(new ByteArrayInputStream(enter.getBytes()));

    assertDoesNotThrow(() -> CommandHandler.run(container,new Scanner(System.in)));

    //Ausgabe testen.
    assertFalse(out.toString().contains("Falsche Eingabe. Versuche 'help' für mehr Informationen."));
    assertTrue(container.size() == 2);
    UserStory.setNextId(0);
    assertTrue(container.getCurrentList().contains(new UserStory("1", "2", "7",
        3,5, 6, 4)));
    assertTrue(container.getCurrentList().contains(
        new UserStory("Titel", "Kriterium", "Projekt", 1,
        2, 3, 4)));
  }

  /**
   * Test für den Store Command.
   */
  @Test
  public void storeTest() {
    UserStory story = new UserStory("1", "2", "3", 4, 5, 6, 7);
    container.addItem(story);
    System.setIn(new ByteArrayInputStream("store\nexit\n".getBytes()));
    assertDoesNotThrow(() -> CommandHandler.run(container,new Scanner(System.in)));
    assertFalse(out.toString().contains("Falsche Eingabe. Versuche 'help' für mehr Informationen."));
    Container.reset();
    container = Container.getInstance();
    container.setPersistenceStrategy(persistenceStrategy);
    assertTrue(container.size() == 0);
    try {
      container.load();
    } catch (PersistenceException e) {
      throw new RuntimeException(e);
    }
    assertTrue(container.size() == 1);
    assertTrue(container.getCurrentList().contains(story));
    Container.reset();
    container = Container.getInstance();
    container.addItem(story);
    System.setIn(new ByteArrayInputStream("store\nexit\n".getBytes()));
    assertDoesNotThrow(() -> CommandHandler.run(container,new Scanner(System.in)));
    assertFalse(out.toString().contains("Falsche Eingabe. Versuche 'help' für mehr Informationen."));
    String lastMSG = out.toString().split("\n")[out.toString().split("\n").length - 2];
    assertEquals("Kritischer Fehler: Der Container kann nicht gespeichert werden.",lastMSG);
    lastMSG = out.toString().split("\n")[out.toString().split("\n").length - 1];
    assertEquals("Es wurde keine PersistenceStrategy gesetzt.",lastMSG);
  }

  /**
   * Test für den Load Command.
   */
  @Test
  public void loadTest() {
    UserStory story = new UserStory("7", "6", "5", 4, 3, 2, 1);
    container.addItem(story);
    try {
      container.store();
    } catch (PersistenceException e) {
      throw new RuntimeException(e);
    }
    Container.reset();
    container = Container.getInstance();
    container.setPersistenceStrategy(persistenceStrategy);
    assertTrue(container.size()==0);
    System.setIn(new ByteArrayInputStream("load\nexit\n".getBytes()));
    assertDoesNotThrow(() -> CommandHandler.run(container,new Scanner(System.in)));
    assertFalse(out.toString().contains("Falsche Eingabe. Versuche 'help' für mehr Informationen."));
    assertTrue(container.size() == 1);
    assertTrue(container.getCurrentList().contains(story));
    Container.reset();
    container = Container.getInstance();
    System.setIn(new ByteArrayInputStream("load\nexit\n".getBytes()));
    assertDoesNotThrow(()-> CommandHandler.run(container,new Scanner(System.in)));
    assertFalse(out.toString().contains("Falsche Eingabe. Versuche 'help' für mehr Informationen."));
    String lastMSG = out.toString().split("\n")[out.toString().split("\n").length - 2];
    assertEquals("Kritischer Fehler: Der Container kann nicht geladen werden.",lastMSG);
    lastMSG = out.toString().split("\n")[out.toString().split("\n").length - 1];
    assertEquals("Es wurde keine PersistenceStrategy gesetzt.",lastMSG);
  }

  /**
   * Reste der Tests, um den Input Stream und den Output Stream zurückzusetzen.
   */
  @AfterEach
  public void tearDown() {
    Container.reset();
    System.setIn(originalInput);
    System.setOut(originalOut);
  }



}
