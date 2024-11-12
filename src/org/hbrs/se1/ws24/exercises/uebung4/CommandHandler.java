package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung4.view.ContainerView;
import sun.misc.Signal;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CommandHandler {
  public static void run(Container container, Scanner scanner) {
    String input;
    while (true) {
      input = scanner.nextLine();

      //Befehl von Parameter separieren.
      String[] befehl = input.split(" ",2);

      switch (befehl[0]) {
        case "exit":
          return;
        case "help":
          printHelp();
          break;
        case "store":
          try {
            container.store();
          } catch (PersistenceException e) {
            System.out.println("Kritischer Fehler: Der Container kann nicht gespeichert werden.");
            System.out.println(e.getMessage());
            return;
          }
          break;
        case "load":
          try {
            container.load();
          } catch (PersistenceException e) {
            System.out.println("Kritischer Fehler: Der Container kann nicht geladen werden.");
            System.out.println(e.getMessage());
            return;
          }
          break;
        case "dump":
          ContainerView.dump(container.getCurrentList(),befehl.length > 1 ? befehl[1] : null);
          break;
        case "enter":
          container.addItem(enterUserStory(input, scanner));
          System.out.println("User Story wurde erfasst.");
          break;

        default:
          System.out.println("Falsche Eingabe. Versuche 'help' für mehr Informationen.");
          break;
      }
    }
  }



  /**
   * Legt eine neue UserStory an und gibt diese zurück.
   * Die benötigten Informationen werden entweder aus dem Input heraus genommen oder einzelt abgefragt.
   *
   * @param input übergibt den initialen Befehl, der auf der Console eingegeben wurde.
   * @return gibt die UserStory zurück.
   */
  private static UserStory enterUserStory(String input, Scanner scann) {
    String[] parameter = input.split("enter\s\"|[\s]?\"[\s]\"|\"$");
    double[] valuesGloger = new double[4];
    if (parameter.length == 8) {
      try{
        valuesGloger[0] = Double.parseDouble(parameter[3]);
        valuesGloger[1] = Double.parseDouble(parameter[4]);
        valuesGloger[2] = Double.parseDouble(parameter[5]);
        valuesGloger[3] = Double.parseDouble(parameter[6]);
      } catch (NumberFormatException e) {
        System.out.println("Die Werte für die Gloger Formel konnten nicht in Zahlen umgewandelt werden. Bitte geben sie alle Angaben erneut ein.");
        enterUserStory("enter", scann);
      }
    }else{
      parameter = new String[8];
      System.out.print("Geben sie den Titel der User Story an: ");
      parameter[1] = scann.nextLine();
      System.out.print("Geben sie die Akzeptanzkriterien User Story an: ");
      parameter[2] = scann.nextLine();
      valuesGloger[0] = enterGloger("Geben sie ihre Bewertung für den Mehrwert an: ", scann);
      valuesGloger[1] = enterGloger("Geben sie ihre Bewertung für die Strafe an: ", scann);
      valuesGloger[2] = enterGloger("Geben sie ihre Bewertung für den Aufwand an: ", scann);
      valuesGloger[3] = enterGloger("Geben sie ihre Bewertung für das Risiko an: ", scann);
      System.out.print("Geben sie den Projekt Namen an: ");
      parameter[7] = scann.nextLine();
    }
    return new UserStory(parameter[1], parameter[2], parameter[7], valuesGloger[0], valuesGloger[2], valuesGloger[3], valuesGloger[1]);
  }

  /**
   * Gibt den help Text auf der Console aus.
   */
  private static void printHelp(){
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
    System.out.println("store: Speichert den aktellen Stand der User Stories");
    System.out.println("load: Lädt den aktuellen Stand der User Stories von der Festplatte");
  }

  /**
   * Ist für die Einlesung von Int werten für die Gloger Formel zuständig und übernimmt die Fehlerbehandlung.
   *
   * @param consoleOutput ist die Ausgabe die einen Auffordert den int Wert einzugeben.
   * @return gibt den erfolgreich eingelesenen int Wert zurück.
   */
  private static double enterGloger(String consoleOutput, Scanner scanner){
    double valueGloger = -1;

    System.out.print(consoleOutput);

    while (valueGloger  <= 0){
      try {
        valueGloger = scanner.nextDouble();
      }catch (InputMismatchException e){
        System.out.print("Der eingegebene Wert ist keine Zahl. Bitte versuchen sie es erneut: ");
        scanner.nextLine();
        continue;
      }
      if(valueGloger <= 0){
        System.out.print("Der Wert ist zu klein: ");
      }
    }
    scanner.nextLine();
    return valueGloger;
  }
}
