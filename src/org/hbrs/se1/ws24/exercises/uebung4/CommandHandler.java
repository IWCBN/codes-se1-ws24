package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import sun.misc.Signal;

public class CommandHandler {
  public static void run(Container container) {
    String input = "";
    while (true) {
      Signal.handle(new Signal("INT"),sig -> System.out.println("Interrupted by Ctrl+C"));
      input = System.console().readLine();

      switch (input) {
        case "exit":
          System.exit(0);
          break;
        case "help":
          System.out.println("Folgende Befehle stehen zur Verfügung:\nexit: Beendet das Programm.\nhelp: Gibt genau diese Liste aus.");
          System.out.println("enter [\"Titel\" \"Akzeptanzkriterium\" \"Mehrwert\" \"Strafe\" \"Aufwand\" \"Risiko\" \"Projekt Name\"]:");
          System.out.println("\tLegt eine neue User Story in dem System an. Folgende Werte werden benötigt:");
          System.out.println("\t\tTitel: Titel der User Story");
          System.out.println("\t\tAkzeptanzkriterium: Beschreibt Testfälle mit denen das erfolgreiche Abschließen einer User Story erreicht werden kann.");
          System.out.println("\t\tMehrwert: Ist eine Zahl, die den Mehrwert für das Unternehmen beziffert.");
          System.out.println("\t\tStrafe: Ist eine Zahl, die die Höhe der Strafe beziffert.");
          System.out.println("\t\tAufwand: Ist eine Zahl, die den Aufwand beziffert um die User Story umzusetzen.");
          System.out.println("\t\tRisiko: Ist eine Zahl, die das Risiko beziffert wenn die User Story nicht umgesetzt wird.");
          System.out.println("\t\tProjekt Name: Gibt den Namen des zugehörigen Projektes aus.");
          System.out.println("store\n\tSpeichert den aktellen Stand der User Stories");
          System.out.println("load\n\tLädt den aktuellen Stand der User Stories von der Festplatte");

          break;
        case "save":
          try {
            container.store();
          } catch (PersistenceException e) {
            System.out.println("Kritischer Fehler: Der Container kann nicht gespeichert werden.");
            System.out.println(e.getMessage());
            System.exit(1);
          }
          break;
        case "load":
          try {
            container.load();
          } catch (PersistenceException e) {
            System.out.println("Kritischer Fehler: Der Container kann nicht geladen werden.");
            System.out.println(e.getMessage());
            System.exit(1);
          }
          break;
        case "dump":
          System.out.println("Tabelle(noch nicht implementiert)");
          break;

        default:
          System.out.println("Falsche Eingabe. Versuche 'help' für mehr Informationen.");
          break;
      }
    }
  }
}
