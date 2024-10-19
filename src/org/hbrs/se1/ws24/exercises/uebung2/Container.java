package org.hbrs.se1.ws24.exercises.uebung2;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.io.*;
import java.util.*;

public class Container{

    private static Container INSTANCE = null;
    List<Member> members = new LinkedList<>();
    private PersistenceStrategy<Member> persistenceStrategy = null;


    private Container() { }

    public static Container getInstance() {
        if (INSTANCE == null) {
            synchronized (Container.class) { //wegen Multithreading
                if (INSTANCE == null) {
                    INSTANCE = new Container();
                }
            }
        }
        return INSTANCE;
    }

    public void setPersistenceStrategy(PersistenceStrategy<Member> persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

    /**
     * Fügt neuen Member zum Container hinzu. Falls schon ein Member mit derselben Member ID
     * vorhanden ist, wird ein Fehler vom Typ ContainerException geworfen.
     *
     * @param member ist der Member der zu dem Container hinzugefügt werden soll.
     * @throws ContainerException wird geworfen, wenn es schon einen Member
     * mit derselben Member ID in dem Container gibt.
     */
     public void addMember(Member member) throws ContainerException{

         if(members.contains(member)){
             throw new ContainerException(member.getID());
         }

         members.add(member);
     }

    /**
     * Löscht den Member aus dem Container heraus, falls dieser bereits orhanden ist.
     * Falls kein Member mit der übergebenen ID vorhanden ist, wird eine Fehlermeldung als String zurückgegeben.
     *
     * @param id ist die ID des Members die gelöscht werden soll.
     * @return gibt bei erfolgreichem/nicht erfolgreichem Löschen eine Nachricht mit der Member ID als String zurück.
     */
     public String deleteMember(int id) {
         for(Member member : members){
             if(member.getID() == id){
                 members.remove(member);
                 return "Folgender Member wurde gelöscht: " + member.getID();
             }
         }
         return "Es existiert kein Member mit Folgender Member ID: " + id;
     }

    /**
     * Gibt auf der Konsole alle Member aus dem Container aus.
     */
     public void dump(){
         for(Member member : members){
             System.out.println(member);
         }
     }

    /**
     * Ermittelt die Anzahl der Member Objekte in dem Container.
     *
     * @return gibt zurück wie viele Member in dem Container vorhanden sind.
     */
     public int size() {
         return members.size();
     }

     public void store()  throws PersistenceException {
         if(persistenceStrategy == null){
             throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es wurde keine PersistenceStrategy gesetzt.");
         }

         try{
             persistenceStrategy.save(members);
         }catch(UnsupportedOperationException e){
             throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Die gewählte persistenceStrategy ist nicht Implementiert");
         }

     }

     public void load() throws PersistenceException {
         if(persistenceStrategy == null){
             throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es wurde keine PersistenceStrategy gesetzt.");
         }
         try{
             members = persistenceStrategy.load();
         }catch(UnsupportedOperationException e){
             throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Die gewählte persistenceStrategy ist nicht Implementiert");
         }
    }
}
