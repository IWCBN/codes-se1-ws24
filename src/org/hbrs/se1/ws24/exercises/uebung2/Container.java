package org.hbrs.se1.ws24.exercises.uebung2;
import java.util.*;

public class Container {
    LinkedList<Member> members;

    public void addMember(Member member) {

        for(Member person : members){
            if(person.getID() == member.getID()){
                throw new ContainerException(member.getID());
            }
        }
        members.add(member);
    }

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
