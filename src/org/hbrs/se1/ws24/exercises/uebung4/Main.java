package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;

public class Main {

  public static void main(String[] args) {
    Container.getInstance().setPersistenceStrategy(new PersistenceStrategyStream<UserStoryInterface>());
    CommandHandler.run(Container.getInstance());

  }
}
