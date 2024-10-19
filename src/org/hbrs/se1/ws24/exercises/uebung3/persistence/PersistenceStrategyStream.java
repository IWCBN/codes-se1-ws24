package org.hbrs.se1.ws24.exercises.uebung3.persistence;

import org.hbrs.se1.ws24.exercises.uebung2.Member;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     * Look-up in Google for further help!
     */
    public void save(List<E> member) throws PersistenceException  {
        try {

            FileOutputStream file = new FileOutputStream(location);

            // Creates an ObjectOutputStream
            ObjectOutputStream output = new ObjectOutputStream(file);


            // writes objects to output stream
            output.writeObject(member);
            output.close();         //Ich würde das gerne in finally verschieben. Leider wird dort die Variable nicht erkannt,

        }catch (Exception e) {
            System.out.println("Folgende Naricht wurde übergeben: " + e.getMessage()); //Muss noch richtig implementiert werden!!!
        }
    }

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
        } catch (Exception e) {
            System.out.println("Fehler muss noch erstellt werden"); //Muss gleich noch implementiert werden.
        }

        return null;
    }
}
