package org.hbrs.se1.ws24.exercises.uebung2;

public class ContainerException extends IllegalArgumentException {

    /**
     * Konstruktor, welcher ein Objekt vom Typ IllegalArgumentException erzeugt und eine individuelle Fehlernachricht übergibt.
     *
     * @param id ist die Member ID, die in der Fehlernachricht angezeigt werden soll.
     */
    public ContainerException(int id) {
        super("Das Member-Objekt mit der ID " + id + " ist bereits vorhanden!");
    }

}
