package org.hbrs.se1.ws24.tests.uebung4;

import org.hbrs.se1.ws24.exercises.uebung4.Container;
import org.hbrs.se1.ws24.exercises.uebung4.UserStory;
import org.hbrs.se1.ws24.exercises.uebung4.UserStoryInterface;
import org.hbrs.se1.ws24.exercises.uebung4.view.ContainerView;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DumpTest {
    private static final PrintStream originalOut = System.out;
    private ByteArrayOutputStream out = null;
    LinkedList<UserStoryInterface> list = null;
    UserStory story;
    UserStory story2;
    UserStory story3;

    /**
     * Initialisierung der Testumgebung mit einer Liste und drei beispielhaften User Stories.
     */
    @BeforeEach
    public void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        UserStory.setNextId(0);
        list = new LinkedList<>();
        story = new UserStory("test", "test", "test", 1, 1, 1, 1);
        story2 = new UserStory("Hallo wie geht es dir heute? Wird das alles Klappen?", "test", "test", 1, 1, 1, 1);
        story3 = new UserStory("Hallo wie geht es dir heute? Wird das alles Klappen?", "test", "test2", 1, 1, 454545454, 1);
    }

    /**
     * Prüft, ob eine Meldung für eine leere Liste in der Methode <code>dump</code> kommt.
     */
    @Test
    public void dumpEmptyListTest(){
        ContainerView.dump(list,null);
        String error = "Container is empty\n";
        assertEquals(error, out.toString());
    }

    /**
     * Prüft anhand einer beispielhaften User Story, ob die Eingaben in der Methode <code>dump</code>
     * richtig funktionieren.
     */
    @Test
    public void dumpNormalListTest(){
        list.addFirst(story);
        ContainerView.dump(list,null);
        String soll = "| ID   | Titel                | Kriterium            | Projekt              | BusinessValue | Effort | Risk | Penalty | Prioritization |\n" +
                "| 1    | test                 | test                 | test                 | 1.0           | 1.0    | 1.0  | 1.0     | 1.0            |\n";
        assertEquals(soll, out.toString());
    }

    /**
     * Prüft, anhand mehrerer beispielhaften User Stories, ob die Eingaben in der Methode <code>dump</code>
     * richtig funktionieren - fokussiert auf die Funktionalität der automatischen Zeilenumbrüche.
     */
    @Test
    public void dumpLongListTest(){
        list.addFirst(story3);
        list.addFirst(story2);
        list.addFirst(story);
        ContainerView.dump(list,null);

        String soll = "| ID   | Titel                | Kriterium            | Projekt              | BusinessValue | Effort | Risk | Penalty | Prioritization |\n" +
                "| 1    | test                 | test                 | test                 | 1.0           | 1.0    | 1.0  | 1.0     | 1.0            |\n" +
                "| 2    | Hallo wie geht es di | test                 | test                 | 1.0           | 1.0    | 1.0  | 1.0     | 1.0            |\n" +
                "|      | r heute? Wird das al |                      |                      |               |        |      |         |                |\n" +
                "|      | les Klappen?         |                      |                      |               |        |      |         |                |\n" +
                "| 3    | Hallo wie geht es di | test                 | test2                | 1.0           | 1.0    | 4.54 | 1.0     | 4.3999999956E- |\n" +
                "|      | r heute? Wird das al |                      |                      |               |        | 5454 |         | 9              |\n" +
                "|      | les Klappen?         |                      |                      |               |        | 54E8 |         |                |\n" +
                "|      |                      |                      |                      |               |        |      |         |                |\n";
        assertEquals(soll, out.toString());
    }

    /**
     * Prüft, ob der Projekt-Filter in der Methode <code>dump</code> funktioniert, anhand
     * von drei beispielhaften User Stories.
     */
    @Test
    public void dumpFilterListTest(){
        list.addFirst(story3);
        list.addFirst(story2);
        list.addFirst(story);
        ContainerView.dump(list,"test");
        String soll = "| ID   | Titel                | Kriterium            | Projekt              | BusinessValue | Effort | Risk | Penalty | Prioritization |\n" +
                "| 1    | test                 | test                 | test                 | 1.0           | 1.0    | 1.0  | 1.0     | 1.0            |\n" +
                "| 2    | Hallo wie geht es di | test                 | test                 | 1.0           | 1.0    | 1.0  | 1.0     | 1.0            |\n" +
                "|      | r heute? Wird das al |                      |                      |               |        |      |         |                |\n" +
                "|      | les Klappen?         |                      |                      |               |        |      |         |                |\n";
        assertEquals(soll, out.toString());
    }


    /**
     * Prüft die Methode <code>dump</code> auf die Ausgabe mehrere Int-Werte (anstatt von
     * einer User Story) in einer Liste.
     */
    @Test
    public void dumpIntListTest(){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        ContainerView.dump(list);
        String soll = "1\n2\n3\n";
        assertEquals(soll, out.toString());
    }

    /**
     * Reset der Tests, um den Output Stream zurückzusetzen.
     */
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}
