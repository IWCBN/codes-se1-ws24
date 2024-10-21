package org.hbrs.se1.ws24.exercises.uebung3.view;

import org.hbrs.se1.ws24.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.Container;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;

/**
 * Diese Klasse ist eine beispielhafte Implementierung eines Clients.
 */
public class Client {

  /**
   * Diese Methode ist eine beispielhafte Implementierung eines Clients.
   *
   * @param aktuellerKontainer ist der aktuelle Container, mit dem gearbeitet werden soll.
   */
  public static void run(Container aktuellerKontainer) {
    aktuellerKontainer.addMember(new ConcreteMember(12));
    aktuellerKontainer.addMember(new ConcreteMember(22));
    aktuellerKontainer.addMember(new ConcreteMember(33));

    try {
      aktuellerKontainer.store();
      aktuellerKontainer.load();
    } catch (PersistenceException e) {
      throw new RuntimeException(e);
    }
    MemberView.dump(aktuellerKontainer.getCurrentList());
  }
}
