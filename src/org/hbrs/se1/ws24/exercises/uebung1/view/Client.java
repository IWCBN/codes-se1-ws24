package org.hbrs.se1.ws24.exercises.uebung1.view;

public class Client {

		/**
		 * Methode zur Ausgabe einer Zahl auf der Console
		 * (auch bezeichnet als CLI, Terminal)
		 *
		 */
		 void display( int aNumber ){
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung (nur) gegen das Interface Translator gewuenscht!

			 Translator t1 = TranslatorFactory.creatTranslator();
			 System.out.println("Das Ergebnis der Berechnung: " + t1.translateNumber(aNumber) );

		 }
}





