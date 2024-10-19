package org.hbrs.se1.ws24.exercises.uebung2;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.io.*;
import java.util.*;

public class Container{

    private static Container INSTANCE = null;
    private File path = null;

    public void setPath(File path) {
        this.path = path;
    }

    public File getPath() {
        return path;
    }

    List<Member> members = new LinkedList<>();

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

    //@Override
    public void save(List<Member> member) throws PersistenceException {
        if(!path.exists()){
            try {
                System.out.println(
                path.createNewFile() + " : " + path.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(path);
            fw.write("MemberID\n");

            for(Member m : member){
                fw.write(m.getID() + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Speichern der Datei", e);
        } finally {
            if(fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    String ort = "C:\\Users\\adkie\\IdeaProjects\\codes-se1-ws24\\src\\org\\hbrs\\se1\\ws24\\exercises\\uebung3\\3file.txt";

    public void store()  {
        /**try {

            FileOutputStream file = new FileOutputStream(ort);

            // Creates an ObjectOutputStream
            ObjectOutputStream output = new ObjectOutputStream(file);


            // writes objects to output stream

            output.writeInt(members.size());

            for(Member member : members){
                output.writeObject(member);
            }

            output.close();
        }

        catch (Exception e) {
            System.out.println("Folgende Naricht wurde übergeben: " + e.getMessage());
        }*/
    }

    public void load(){
        try{
            // Reads data using the ObjectInputStream
            FileInputStream fileStream = new FileInputStream(ort);
            ObjectInputStream objStream = new ObjectInputStream(fileStream);

            int objectCount = objStream.readInt();

            for(int i = 0; i < objectCount; i++){
                System.out.println(objStream.readObject());
            }

            objStream.close();
        } catch (Exception e) {

        }
    }

    /*@Override
    public List<Member> load() throws PersistenceException {
        return List.of();
    }*/
}
