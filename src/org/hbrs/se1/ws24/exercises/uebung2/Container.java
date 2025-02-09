package org.hbrs.se1.ws24.exercises.uebung2;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyMongoDB;

import java.util.*;

public class Container{

    private static Container INSTANCE = null;
    List<Member> members = new LinkedList<>();
    private PersistenceStrategy<Member> persistenceStrategy = null;

    /**
     * Überschreibt den Konstruktor durch einen privaten Konstruktor, damit er nicht mehr von außerhalb der Klasse aufgerufen werden kann.
     */
    private Container() { }

    /**
     * <h1>Singleton</h1>
     * Verhindert das mehrfache Instanziieren der Klasse.
     * <br>
     * Die synchronized Methode verhindert das auf Grund von Multi Threading mehrere Instanzen zum selben Zeitpunkt erzeugt werden können.
     *
     * @return gibt eine Instanz der Container Klasse zurück
     */
    public static Container getInstance() {
        if(INSTANCE == null) {
            synchronized (Container.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Container();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * @param persistenceStrategy kann gesetzt werden für die store und load Methode.
     */
    public void setPersistenceStrategy(PersistenceStrategy<Member> persistenceStrategy) {
        if(persistenceStrategy instanceof PersistenceStrategyMongoDB) {
            throw new UnsupportedOperationException("Die gewählte persistenceStrategy ist nicht implementiert");
        }
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
     public void addMember(Member member) throws ContainerException {

         if(members.contains(member)) {
             throw new ContainerException(member.getID());
         }

         members.add(member);
     }

    /**
     * Löscht den Member aus dem Container heraus, falls dieser bereits vorhanden ist.
     * Falls kein Member mit der übergebenen ID vorhanden ist, wird eine Fehlermeldung als String zurückgegeben.
     *
     * @param id ist die ID des Members die gelöscht werden soll.
     * @return gibt bei erfolgreichem/nicht erfolgreichem Löschen eine Nachricht mit der Member ID als String zurück.
     */
     public String deleteMember(int id) {
         for(Member member : members) {
             if(member.getID() == id) {
                 members.remove(member);
                 return "Folgender Member wurde gelöscht: " + member.getID();
             }
         }
         return "Es existiert kein Member mit folgender Member ID: " + id;
     }

    /**
     * Gibt eine Kopie der LinkList members aus.
     *
     * @return gibt eine Kopie der LinkList members aus
     */
     public LinkedList getCurrentList(){
         return new LinkedList(members);
     }

    /**
     * Ermittelt die Anzahl der Member Objekte in dem Container.
     *
     * @return gibt zurück wie viele Member in dem Container vorhanden sind.
     */
     public int size() {
         return members.size();
     }

    /**
     * Speichert die LinkList members auf der Festplatte ab.
     *
     * @throws PersistenceException wird geworfen, wenn ein Fehler beim Speichern auftritt.
     */
     public void store() throws PersistenceException {
         if(persistenceStrategy == null) {
             throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es wurde keine PersistenceStrategy gesetzt.");
         }

         try {
             persistenceStrategy.save(members);
         } catch(UnsupportedOperationException e) {
             throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Die gewählte persistenceStrategy ist nicht implementiert");
         }

     }

    /**
     * Lädt die gespeicherte LinkList und überschreibt damit die LinkList members
     *
     * @throws PersistenceException wird geworfen, wenn ein Fehler beim Laden der gespeicherten Members auftritt.
     */
     public void load() throws PersistenceException {
         if(persistenceStrategy == null) {
             throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es wurde keine PersistenceStrategy gesetzt.");
         }

         try {
             members = persistenceStrategy.load();
         } catch(UnsupportedOperationException e) {
             throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Die gewählte persistenceStrategy ist nicht implementiert");
         }
    }
}
