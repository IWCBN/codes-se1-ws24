package org.hbrs.se1.ws24.exercises.uebung2;

import java.io.Serializable;

/**
 * Diese Klasse dient als Beispiel-Implementierung eines Members.
 */
public class ConcreteMember implements Member, Serializable {

  private final Integer ID;

  /**
   * Konstruktor, welcher ein Member-Objekt mit vorgegebener ID erzeugt.
   *
   * @param id ist die ID dieses Member-Objekts
   */
  public ConcreteMember(Integer id){
    this.ID =id;
  }

  /**
   * Die ID ist über einen Konstruktor einer Klasse, welches dieses Interface implementiert,
   * zu setzen. Die ID darf nicht innerhalb des Container-Objekts gesetzt werden.
   * Die ID dient als Primärschlüssel zur Unterscheidung aller Member-Objekte.
   */
  @Override
  public Integer getID() {
    return ID;
  }


  /**
   * Prüft, ob dieses Member-Objekt(<code>this</code>) mit dem übergebenen Objekt(<code>obj</code>) identisch ist
   *
   * @param obj das zu vergleichende Objekt
   * @return true, wenn die Objekte gleich sind, sonst false
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj){
      return true;
    }
    if(obj instanceof Member) {
      return this.getID().equals(((Member) obj).getID());
    }
    return false;
  }

  /**
   * Gibt einen String zurück welcher wie folgt aufgebaut ist:
   *<br>
   * <q>Member (ID = <code>ID</code>)</q>
   *
   * @return Member (ID = <code>ID</code>)
   */
  @Override
  public String toString() {
    return "Member (ID = " + ID + ")";
  }

}
