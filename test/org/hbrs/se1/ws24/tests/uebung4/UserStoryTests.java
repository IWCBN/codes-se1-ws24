package org.hbrs.se1.ws24.tests.uebung4;

import org.hbrs.se1.ws24.exercises.uebung4.UserStory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserStoryTests {

  private UserStory story;

  /**
   * UserStory wird initialisiert
   */
  @BeforeEach
  public void setUp() {
    story = new UserStory("titel", "acceptanceCriterion", "project", 1, 1, 1, 1);
  }

  /**
   * Testet die getTitle() Methode
   */
  @Test
  public void getTitleTest() {
    assertEquals("titel", story.getTitle());
  }

  /**
   * Testet die getAcceptanceCriterion() Methode
   */
  @Test
  public  void getAcceptanceCriterionTest() {
    assertEquals("acceptanceCriterion", story.getAcceptanceCriterion());
  }

  /**
   * Testet die getProject() Methode
   */
  @Test
  public void getProjectTest() {
    assertEquals("project", story.getProject());
  }

  /**
   * Testet die getID() Methode
   */
  @Test
  public void getIDTest() {
    assertEquals(1, story.getID());
  }

  /**
   * Testet die getBusinessValue() Methode
   */
  @Test
  public void getPrioritizationTest() {
    assertEquals(1, story.getPrioritization());
  }

  /**
   * Testet die setProject() Methode
   */
  @Test
  public void setProjectTest() {
    assertEquals("project", story.getProject());
    story.setProject("newProject");
    assertEquals("newProject", story.getProject());
  }

  /**
   * Testet die setAcceptanceCriterion() Methode
   */
  @Test
  public void setAcceptanceCriterionTest() {
    assertEquals("acceptanceCriterion", story.getAcceptanceCriterion());
    story.setAcceptanceCriterion("newAcceptanceCriterion");
    assertEquals("newAcceptanceCriterion", story.getAcceptanceCriterion());
  }

  /**
   * Testet die setTitle() Methode
   */
  @Test
  public void setTitleTest() {
    assertEquals("titel", story.getTitle());
    story.setTitle("newTitle");
    assertEquals("newTitle", story.getTitle());
  }

  /**
   * Testet die equals() Methode
   */
  @Test
  public void equalsTest() {
    UserStory.setNextId(0);
    UserStory story2 = new UserStory("1", "2", "3", 4, 5, 6, 7);
    assertEquals(story, story2);
  }

  /**
   * Setzt die ID der UserStory zurück.
   */
  @AfterEach
  public void tearDown() {
    UserStory.setNextId(0);
  }

}
