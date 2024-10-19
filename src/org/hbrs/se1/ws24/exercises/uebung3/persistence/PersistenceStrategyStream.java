package org.hbrs.se1.ws24.exercises.uebung3.persistence;

import org.hbrs.se1.ws24.exercises.uebung2.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Bekommt die Liste Member und speichert diese auf dem Datenträger.
     *
     * @param member wird auf dem Datenträger persistent gespeichert.
     * @throws PersistenceException wird geworfen bei einem Fehler während dem Speicherprozess.
     */
    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     * Look-up in Google for further help!
     */
    public void save(List<E> member) throws PersistenceException  {
        if(location == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStorageLocationSet, "Der angegebene Speicherort ist ungültig");
        }

        try {

            FileOutputStream file = new FileOutputStream(location);

            // Creates an ObjectOutputStream
            ObjectOutputStream output = new ObjectOutputStream(file);


            // writes objects to output stream
            output.writeObject(member);
            output.close();         //Ich würde das gerne in finally verschieben. Leider wird dort die Variable nicht erkannt,

        }catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStorageLocationSet, "Der angegebene Speicherort ist ungültig");
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.IOError, "Das Speicher der Liste war nicht erfolgreich. Weitere Information: " + e.getMessage());
        }
    }

    /**
     * Ladet die zuletzt gespeicherte Member Liste von dem Datenträger.
     * @return gibt die geladene Liste zurück.
     * @throws PersistenceException wirft einen Fehler, wenn das Laden von dem Datenträger nicht geklappt hat.
     */
    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException  {
        // Some Coding hints ;-)

        // ObjectInputStream ois = null;
        // FileInputStream fis = null;
        // List<...> newListe =  null;
        //
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        // fis = new FileInputStream( " a location to a file" );

        // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)
        // ois = new ObjectInputStream(fis);

        // Reading and extracting the list (try .. catch ommitted here)
        // Object obj = ois.readObject();

        // if (obj instanceof List<?>) {
        //       newListe = (List) obj;
        // return newListe

        // and finally close the streams

        try{

            if(location == null){
                throw new PersistenceException(PersistenceException.ExceptionType.NoStorageLocationSet, "Der angegebene Speicherort ist ungültig");
            }

            // Reads data using the ObjectInputStream
            FileInputStream fileStream = new FileInputStream(location);
            ObjectInputStream objStream = new ObjectInputStream(fileStream);

            Object obj = objStream.readObject();

            List<E> newList = null;

            if (obj instanceof List<?>) {
                newList = (List) obj;
            }
            objStream.close();

            return newList;
        } catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStorageLocationSet, "Der angegebene Speicherort ist ungültig");
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.IOError, "Das Speichern der Liste war nicht erfolgreich. Weitere Information: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, e.getMessage());
        }
    }
}
