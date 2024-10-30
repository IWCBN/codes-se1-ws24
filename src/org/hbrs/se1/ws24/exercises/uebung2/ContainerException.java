package org.hbrs.se1.ws24.exercises.uebung2;

public class ContainerException extends IllegalArgumentException {

    /**
     * Konstruktor, welcher ein Objekt vom Typ IllegalArgumentException erzeugt und eine individuelle
     * Fehlernachricht übergibt. Hier wird eine Fehlernachricht für die Member ID angegeben.
     * <p>
     * Beispiel:
     * <code>Das Member-Objekt mit der ID 1 ist bereits vorhanden!</code>
     *</p>
     * @param id ist die Member ID, die in der Fehlernachricht angezeigt werden soll.
     */
    public ContainerException(int id) {
        super("Das Member-Objekt mit der ID " + id + " ist bereits vorhanden!");
    }

    /**
     * Konstruktor, welcher ein Objekt vom Typ IllegalArgumentException erzeugt und die möglichkeit gibt
     * eine Fehlermeldung anzugeben.
     *
     * @param message ist die Fehlernachricht
     */
    public ContainerException(String message) {
        super(message);
    }

    /**
     * Konstruktor, welcher ein Objekt vom Typ IllegalArgumentException erzeugt und eine
     * individuelle Fehlernachricht übergeben. Hier wird eine Fehlernachricht für ein gegebenes Objekt angegeben.
     * Zum Generieren des Textes wird die toString()-Methode der Klasse Object genutzt.
     * <p>
     * Beispiel:
     * <code>Das Objekt: o.toString() ist bereits vorhanden!</code>
     * </p>
     * @param o
     */
    public ContainerException(Object o) {
        super("Das Objekt: " + o + " ist bereits vorhanden!");
    }

}
