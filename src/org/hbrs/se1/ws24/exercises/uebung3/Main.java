package org.hbrs.se1.ws24.exercises.uebung3;

import org.hbrs.se1.ws24.exercises.uebung2.Container;
import org.hbrs.se1.ws24.exercises.uebung2.Member;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws24.exercises.uebung3.view.Client;

public class Main {
    public static void main(String[] args) {
        Container derKontainer = Container.getInstance();
        PersistenceStrategyStream<Member> persistenceStrategy = new PersistenceStrategyStream<>();
        derKontainer.setPersistenceStrategy(persistenceStrategy);

        Client.run(derKontainer);

    }
}