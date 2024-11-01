package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import sun.misc.Signal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CommandHandler {
  public static void run(Container container) {
    String input = "";
    while (true) {
      Scanner sc = new Scanner(System.in);
      input = sc.nextLine();

      //Befehl von Parameter separieren.
      String[] befehl = input.split(" ");

      switch (befehl[0]) {
        case "exit":
          System.exit(0);
          break;
        case "help":
          printHelp();
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
        case "enter":
          sc.close();
          container.addItem(enterUnserStory(input));
          break;

        default:
          System.out.println("Falsche Eingabe. Versuche 'help' für mehr Informationen.");
          break;
      }
    }
  }
  private static UserStory enterUnserStory(String input) {
    String[] parameter = input.split("enter\s\"|[\s]?\"[\s]\"|\"$");
    UserStory newUserStory;
    int[] valuesGloger = new int[4];
    if (parameter.length == 8) {
      try{
        valuesGloger[0] = Integer.parseInt(parameter[3]);
        valuesGloger[1] = Integer.parseInt(parameter[4]);
        valuesGloger[2] = Integer.parseInt(parameter[5]);
        valuesGloger[3] = Integer.parseInt(parameter[6]);
      } catch (NumberFormatException e) {
        System.out.println("Die Werte für die Gloger Formel konnten nicht in Zahlen umgewandelt werden. Bitte geben sie alle Angaben erneut ein.");
        enterUnserStory("enter");
      }
    }else{
      parameter = new String[8];
      Scanner sc1 = new Scanner(System.in);
      System.out.print("Geben sie den Titel der User Story an: ");
      parameter[1] = sc1.nextLine();
      System.out.print("Geben sie die Akzeptanzkriterien User Story an: ");
      parameter[2] = sc1.nextLine();
      valuesGloger[0] = enterGloger("Geben sie ihre Bewertung für den Mehrwert an: ");
      valuesGloger[1] = enterGloger("Geben sie ihre Bewertung für die Strafe an: ");
      valuesGloger[2] = enterGloger("Geben sie ihre Bewertung für den Aufwand an: ");
      valuesGloger[3] = enterGloger("Geben sie ihre Risiko an: ");
      System.out.print("Geben sie den Projekt Namen an: ");
      parameter[7] = sc1.nextLine();
      sc1.close();
    }
    return new UserStory(parameter[1], parameter[2], parameter[7], valuesGloger[0], valuesGloger[2], valuesGloger[3], valuesGloger[1]);
  }



  private static void printHelp(){
    System.out.println("\nFolgende Befehle stehen zur Verfügung:\nexit: Beendet das Programm.\nhelp: Gibt genau diese Liste aus.");
    System.out.println("enter [\"Titel\" \"Akzeptanzkriterium\" \"Mehrwert\" \"Strafe\" \"Aufwand\" \"Risiko\" \"Projekt Name\"]:");
    System.out.println("\tLegt eine neue User Story in dem System an. Folgende Werte werden benötigt:");
    System.out.println("\t\tTitel: Titel der User Story");
    System.out.println("\t\tAkzeptanzkriterium: Beschreibt Testfälle mit denen das erfolgreiche Abschließen einer User Story erreicht werden kann.");
    System.out.println("\t\tMehrwert: Ist eine Zahl, die den Mehrwert für das Unternehmen beziffert.");
    System.out.println("\t\tStrafe: Ist eine Zahl, die die Höhe der Strafe beziffert.");
    System.out.println("\t\tAufwand: Ist eine Zahl, die den Aufwand beziffert um die User Story umzusetzen.");
    System.out.println("\t\tRisiko: Ist eine Zahl, die das Risiko beziffert wenn die User Story nicht umgesetzt wird.");
    System.out.println("\t\tProjekt Name: Gibt den Namen des zugehörigen Projektes aus.");
    System.out.println("store: Speichert den aktellen Stand der User Stories");
    System.out.println("load: Lädt den aktuellen Stand der User Stories von der Festplatte\n");
  }

  private static int enterGloger(String consoleOutput){
    Scanner sc2 = new Scanner(System.in);
    System.out.print(consoleOutput);
    try{
      return  sc2.nextInt();
    } catch (InputMismatchException e) {
      return enterGloger("Der eingegebene Wert ist keine Zahl. Bitte Versuchen sie es erneut:");
    }finally{
      sc2.close();
    }
  }
}
