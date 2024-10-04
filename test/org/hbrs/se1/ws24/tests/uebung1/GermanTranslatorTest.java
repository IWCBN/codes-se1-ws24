package org.hbrs.se1.ws24.tests.uebung1;
import static org.junit.jupiter.api.Assertions.*;
import org.hbrs.se1.ws24.exercises.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws24.exercises.uebung1.control.Translator;
import org.junit.jupiter.api.Test;

public class GermanTranslatorTest {

    /**
     * Testet die Methode <code>translateNumber</code> aus der Klasse <code>org.hbrs.se1.ws24.exercises.uebung1.GermanTranslator</code>.
     */
    @Test
    public void translateNumber() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("eins" , translator.translateNumber(1));
        assertEquals("fünf" , translator.translateNumber(5));
        assertEquals("zehn" , translator.translateNumber(10));
        assertEquals("Übersetzung der Zahl 0 nicht möglich. Version: " + Translator.version, translator.translateNumber(0));
        assertEquals("Übersetzung der Zahl 11 nicht möglich. Version: " + Translator.version, translator.translateNumber(11));
        assertEquals("Übersetzung der Zahl -11 nicht möglich. Version: " + Translator.version, translator.translateNumber(-11));
    }

}