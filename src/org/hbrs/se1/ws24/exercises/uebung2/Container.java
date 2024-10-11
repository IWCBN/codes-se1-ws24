package org.hbrs.se1.ws24.exercises.uebung2;
import java.util.*;

public class Container {
    LinkedList<Member> members;


    /**
     * Fügt neuen member zum Container hinzu. Falls schon ein Member mit derselben Member ID vorhanden ist wird ein Fehler
     * vom Typ ContainerException geworfen.
     *
     * @param member ist der Member der zu dem Container hinzugefügt werden soll.
     * @throws ContainerException wird geworfen, wenn es schon einen Member mit derselben Member ID in dem Container vorhanden ist.
     */
    public void addMember(Member member) throws ContainerException{

        for(Member person : members){
            if(person.getID() == member.getID()){
                throw new ContainerException(member.getID());
            }
        }
        members.add(member);
    }

    /**
     * Löscht den Member aus dem Container heraus, falls dieser Vorhanden ist. Falls kein Member mit der übergebenen ID
     * vorhanden ist, wird eine Fehlermeldung als String zurückgegeben.
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


}
