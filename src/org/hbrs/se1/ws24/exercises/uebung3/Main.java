package org.hbrs.se1.ws24.exercises.uebung3;

import org.hbrs.se1.ws24.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.Container;
import org.hbrs.se1.ws24.exercises.uebung2.Member;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File path = new File(args[0]);
        Container derKontainer = Container.getInstance();

        derKontainer.addMember(new ConcreteMember(12));
        derKontainer.addMember(new ConcreteMember(22));
        derKontainer.addMember(new ConcreteMember(33));

        PersistenceStrategyStream<Member> persistenceStrategy = new PersistenceStrategyStream<>();
        PersistenceStrategyMongoDB<Member> mongoDB1 = new PersistenceStrategyMongoDB<Member>();

        derKontainer.setPersistenceStrategy(persistenceStrategy);

        try{
            derKontainer.store();
            derKontainer.load();
        }catch(PersistenceException e){
            System.out.println(e.getMessage());
        }

        derKontainer.dump();

    }
}