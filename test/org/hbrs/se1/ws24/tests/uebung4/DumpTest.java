package org.hbrs.se1.ws24.tests.uebung4;

import org.hbrs.se1.ws24.exercises.uebung4.Container;
import org.hbrs.se1.ws24.exercises.uebung4.UserStory;
import org.hbrs.se1.ws24.exercises.uebung4.UserStoryInterface;
import org.hbrs.se1.ws24.exercises.uebung4.view.ContainerView;
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

    @BeforeEach
    public void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        list = new LinkedList<>();
        story = new UserStory("test", "test", "test", 1, 1, 1, 1);
        story2 = new UserStory("Hallo wie geht es dir heute? Wird das alles Klappen?", "test", "test", 1, 1, 1, 1);
        story3 = new UserStory("Hallo wie geht es dir heute? Wird das alles Klappen?", "test", "test", 1, 1, 454545454, 1);
    }

    @Test
    public void dumpEmptyListTest(){
        ContainerView.dump(list,null);
        String error = "Container is empty\r\n";
        assertEquals(error, out.toString());
    }

    @Test
    public void dumpNormalListTest(){
        list.addFirst(story);
        ContainerView.dump(list,null);
        String soll = "| ID   | Titel                | Kriterium            | Projekt              | BusinessValue | Effort | Risk | Penalty | Prioritization |\r\n| 1    | test                 | test                 | test                 | 1.0           | 1.0    | 1.0  | 1.0     | 1.0            |\r\n";
        //assertEquals(soll, out.toString());
        System.setOut(originalOut);
        System.out.println(out.toString());

        System.out.println(soll);

        assertEquals(soll, out.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}
