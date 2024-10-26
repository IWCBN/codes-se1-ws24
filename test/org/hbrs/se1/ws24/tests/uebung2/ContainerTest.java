/*
package org.hbrs.se1.ws24.tests.uebung2;


import org.hbrs.se1.ws24.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.Container;
import org.hbrs.se1.ws24.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
//import java.lang.reflect.Field;
//import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ContainerTest {

  //Der folgende Block ist für das Auslesen der internen Member-Liste
//  private Field memberList;
//  private List<Member> internalMembers;

  //Der folgende Block ist für das Testen der Konsolenausgabe
  private ByteArrayOutputStream outputStream;
  public static final PrintStream originalOut = System.out; //Speicherung des originalen Streams der Console

  //Der folgende Block ist für die Testdaten
  public static final int[] MEMBERSID = {1,6}; //Test IDs für Member Interface
  private Container toTest;
  private Member m1,m2;


  */
/**
   * Setup methode um die Tests zu isolieren. 
   * <br>
   * Wichtig ist hier zu erwähnen, dass System.setOut verwendet wird, um die Ausgabe der Konsole auszuwerten.
   * Außerdem wird hier die Kapselung von dem Container umgangen, um die Add und Remove Methoden zu testen.
   * 
   *//*

  @BeforeEach
  public void setUp(){
    toTest = Container.getInstance();
    toTest.setPersistenceStrategy(null);
    m1 = new ConcreteMember(MEMBERSID[0]);
    m2 = new ConcreteMember(MEMBERSID[1]);

    //Output abgreifen und nach ByteArrayOutputStream umleiten
    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

  }

  //Tests der Size-Methode
  @Test
  public void testSizeEmpty() {
    assertEquals(0, toTest.size());
  }

  @Test
  public void testSizeOne() {
    toTest.addMember(m1);
    assertEquals(1, toTest.size());
  }

  @Test
  public void testSizeTwo() {
    toTest.addMember(m1);
    toTest.addMember(m2);
    assertEquals(2, toTest.size());
  }

  

  //Tests der Dump-Methode
  @Test
  public void testDumpZero() {
    toTest.dump();
    assertEquals("", outputStream.toString());
  }

  @Test
  public void testDumpOne() {
    toTest.addMember(m1);
    toTest.dump();
    assertEquals("Member (ID = 1)\r\n", outputStream.toString());
  }

  @Test
  public void testDumpTwo() {
    toTest.addMember(m1);
    toTest.addMember(m2);
    toTest.dump();
    assertEquals("Member (ID = 1)\r\nMember (ID = 6)\r\n", outputStream.toString());
  }


  //Tests der Add-Methode
  @Test
  public void testAddMember() {
    assertEquals(0, toTest.size());
    toTest.addMember(m1);
    toTest.dump();
    assertTrue(outputStream.toString().contains("Member (ID = 1)"));
    assertEquals(1, toTest.size());
  }

  @Test
  public void testAddMultipleMembers() {
    assertEquals(0, toTest.size());
    toTest.addMember(m1);
    toTest.addMember(m2);
    toTest.dump();
    assertTrue(outputStream.toString().contains("Member (ID = 1)"));
    assertTrue(outputStream.toString().contains("Member (ID = 6)"));
    assertEquals(2, toTest.size());
  }

  //Tests der Add-Methode mit Exceptions
  @Test
  public void testAddMemberException(){
    toTest.addMember(m1);
    assertThrowsExactly(ContainerException.class, () -> toTest.addMember(m1));
  }

  @Test
  public void testAddMultipleMembersException(){
    toTest.addMember(m1);
    toTest.addMember(m2);
    assertThrowsExactly(ContainerException.class, () -> toTest.addMember(m1));
    assertThrowsExactly(ContainerException.class, () -> toTest.addMember(m2));
  }

  @Test
  public void testAddDifferentMemberException(){
    Member m3 = new ConcreteMember(MEMBERSID[0]); //Erzeugt einen neuen Member mit der selben Member ID
    toTest.addMember(m1);
    assertThrowsExactly(ContainerException.class, () -> toTest.addMember(m3));
  }

  //Tests der Remove-Methode
  @Test
  public void testRemoveMemberZero(){
    assertEquals("Es existiert kein Member mit Folgender Member ID: 0", toTest.deleteMember(0));
  }

  @Test
  public void testRemoveMemberOne(){
    toTest.addMember(m1);
    assertEquals(1,toTest.size());
    assertEquals("Folgender Member wurde gelöscht: 1", toTest.deleteMember(m1.getID()));
    assertEquals(0, toTest.size());
  }

  @Test
  public void testRemoveMemberTwo(){
    toTest.addMember(m1);
    toTest.addMember(m2);
    assertEquals(2, toTest.size());
    assertEquals("Folgender Member wurde gelöscht: 1", toTest.deleteMember(m1.getID()));
    toTest.dump();
    assertFalse(outputStream.toString().contains("Member (ID = 1)"));
    assertTrue(outputStream.toString().contains("Member (ID = 6)"));
    assertEquals(1, toTest.size());
  }

  @Test
  public void testRemoveMemberOneNotExisting(){
    toTest.addMember(m1);
    assertEquals(1, toTest.size());
    assertEquals("Es existiert kein Member mit Folgender Member ID: 0", toTest.deleteMember(0));
    toTest.dump();
    assertTrue(outputStream.toString().contains("Member (ID = 1)"));
    assertEquals(1, toTest.size());
  }

  @Test
  public void testRemoveMemberManyNotExisting() {
    toTest.addMember(m1);
    toTest.addMember(m2);
    assertEquals(2, toTest.size());
    assertEquals("Es existiert kein Member mit Folgender Member ID: 0", toTest.deleteMember(0));
    toTest.dump();
    assertTrue(outputStream.toString().contains("Member (ID = 1)"));
    assertTrue(outputStream.toString().contains("Member (ID = 6)"));
    assertEquals(2, toTest.size());
  }

  //Reset für folgende Tests und zurücksetzen des Streams System.out.
  @AfterEach
  public void reste(){
    toTest = null;
    m1=null;
    m2=null;

    //Setze den originalen Stream auf System.out zurück
    System.setOut(originalOut);
    outputStream = null;

  }

}
*/
