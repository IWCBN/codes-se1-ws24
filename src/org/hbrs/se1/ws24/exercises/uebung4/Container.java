package org.hbrs.se1.ws24.exercises.uebung4;
import org.hbrs.se1.ws24.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyMongoDB;

import java.util.*;


public class Container{

    private static Container INSTANCE = null;
    List<UserStoryInterface> userStorys = new LinkedList<>();
    private PersistenceStrategy<UserStoryInterface> persistenceStrategy = null;

    private Container() { }

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

    public void setPersistenceStrategy(PersistenceStrategy<UserStoryInterface> persistenceStrategy) {
        if(persistenceStrategy instanceof PersistenceStrategyMongoDB) {
            throw new UnsupportedOperationException("Die gewählte persistenceStrategy ist nicht implementiert");
        }
        this.persistenceStrategy = persistenceStrategy;
    }

     public void addUserStory(UserStoryInterface userStory) throws ContainerException {

         if(this.userStorys.contains(userStory)) {
             throw new ContainerException(userStory.getID());
         }

         this.userStorys.add(userStory);
     }

     public String deleteMember(int id) {

         return "Es existiert kein Member mit folgender Member ID: " + id;
     }

     public LinkedList getCurrentList(){
         return new LinkedList(userStorys);
     }

     public int size() {
         return userStorys.size();
     }

     public void store() throws PersistenceException {
         if(persistenceStrategy == null) {
             throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es wurde keine PersistenceStrategy gesetzt.");
         }

         try {
             persistenceStrategy.save(userStorys);
         } catch(UnsupportedOperationException e) {
             throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Die gewählte persistenceStrategy ist nicht implementiert");
         }

     }

     public void load() throws PersistenceException {
         if(persistenceStrategy == null) {
             throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es wurde keine PersistenceStrategy gesetzt.");
         }

         try {
             userStorys = persistenceStrategy.load();
         } catch(UnsupportedOperationException e) {
             throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Die gewählte persistenceStrategy ist nicht implementiert");
         }
    }
}
