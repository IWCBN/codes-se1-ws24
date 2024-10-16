package org.hbrs.se1.ws24.exercises.uebung2;
import java.util.*;

public class Container {
    LinkedList<Member> members;

    public void addMember(Member member) {

        for(Member person : members){
            if(person.getID() == member.getID()){
                throw new ContainerException();
            }
        }
        members.add(member);
    }
}
