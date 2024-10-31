package org.hbrs.se1.ws24.exercises.uebung4;
import org.hbrs.se1.ws24.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyMongoDB;

import java.util.*;


/**
 * Die Klasse <code>Container</code> implementiert ein Container-Objekt zum
 * Speichern von generischen Objekten <code>T</code>. Sie verfügt über einen Singleton-Zugriff auf die
 * Instanz.
 *
 * <p>Die Klasse verfügt über Methoden um eine Liste der Objekte in der
 * Instanz zu verwalten.</p>
 *
 * <p>Die Klasse implementiert die Persistenz des Containers mittels
 * der {@link PersistenceStrategy}-Klasse.</p>
 *
 * @version 1.0
 * @since 2020-11-04
 * @param <T> sind die generischen Objekte, die in dem Container gespeichert werden.
 */
public class Container<T> {
  private static volatile Container<?> INSTANCE = null;
  private List<T> items = new LinkedList<>();
  private PersistenceStrategy<T> persistenceStrategy = null;

  /**
   * Privater Konstruktor um eine Instanzierung von außen zu vermeiden.
   */
  private Container() {}

  /**
   * Diese Methode gibt die Instanz des Containers zurück.
   * Falls noch kein Container existieren sollte, wird einer erstellt.
   *
   * @return die Instanz der {@link Container}-Klasse
   * @param <T> ist der Type der generischen Objekte
   */
  @SuppressWarnings("unchecked")
  public static <T> Container<T> getInstance() {
    if (INSTANCE == null) {
      synchronized (Container.class) {
        if (INSTANCE == null) {
          INSTANCE = new Container<>();
        }
      }
    }
    return (Container<T>) INSTANCE;
  }

  /**
   * Mit dieser Methode kann man die {@link PersistenceStrategy} festlegen für den Container.
   *
   * @param persistenceStrategy ist die gewählte {@link PersistenceStrategy}
   */
  public void setPersistenceStrategy(PersistenceStrategy<T> persistenceStrategy) {
    if (persistenceStrategy instanceof PersistenceStrategyMongoDB) {
      throw new UnsupportedOperationException("Die gewählte persistenceStrategy ist nicht implementiert");
    }
    this.persistenceStrategy = persistenceStrategy;
  }

  /**
   * Fügt eine {@link T} Objekt hinzu, sofern es nicht schon vorhanden ist.
   *
   * @param item ist das Object {@link T} welches hinzugefügt werden soll.
   * @throws ContainerException wird geworfen wenn <code>item</code> schon vorhanden ist.
   */
  public void addItem(T item) throws ContainerException {
    if (this.items.contains(item)) {
      throw new ContainerException(item);
    }
    this.items.add(item);
  }

  /**
   * Gibt eine Kopie der gespeicherten Liste<{@link T}> als {@link LinkedList} zurück.
   * @return Kopie der gespeicherten Liste
   */
  public LinkedList<T> getCurrentList() {
    return new LinkedList<>(items);
  }

  /**
   * Gibt die Anzahl der Elemente in dem Container zurück.
   *
   * @return Anzahl der Elemente im Container
   */
  public int size() {
    return items.size();
  }

  /**
   * Speichert die aktuellen Elemente mit der angegebenen {@link PersistenceStrategy}.
   *
   * @throws PersistenceException wenn keine {@link PersistenceStrategy} gesetzt ist oder
   *         wenn die gewählte {@link PersistenceStrategy} nicht implementiert ist.
   */
  public void store() throws PersistenceException {
    if (persistenceStrategy == null) {
      throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es wurde keine PersistenceStrategy gesetzt.");
    }
    try {
      persistenceStrategy.save(items);
    } catch (UnsupportedOperationException e) {
      throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Die gewählte persistenceStrategy ist nicht implementiert");
    }
  }

  /**
   * Lädt die aktuellen Elemente mit der angegebenen {@link PersistenceStrategy} in den <code>Container</code>.
   *
   * @throws PersistenceException wenn keine {@link PersistenceStrategy} gesetzt ist oder
   *         wenn die gewählte {@link PersistenceStrategy} nicht implementiert ist.
   */
  public void load() throws PersistenceException {
    if (persistenceStrategy == null) {
      throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es wurde keine PersistenceStrategy gesetzt.");
    }
    try {
      items = persistenceStrategy.load();
    } catch (UnsupportedOperationException e) {
      throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Die gewählte persistenceStrategy ist nicht implementiert");
    }
  }
}
