package org.hbrs.se1.ws24.exercises.uebung9;

import javax.print.Doc;
import java.io.UnsupportedEncodingException;

public class TestClient {
    public static void main(String[] args) throws UnsupportedEncodingException {
        ComplexDocument doc0 = new ComplexDocument();
        doc0.setID(0);

        TextDocument doc2 = new TextDocument("Die Klausur im Fach SE findet bald statt!", TextDocument.Encoding.UTF8);
        doc2.setID(2);

        ComplexDocument doc3 = new ComplexDocument();
        doc3.setID(3);

        GraficDocument doc4 = new GraficDocument("localhost:8080");
        doc4.setID(4);

        TextDocument doc5 = new TextDocument("Software Engineering ist eine Vorlesung in den Studiengaenge BIS und BCS", TextDocument.Encoding.UTF32);
        doc5.setID(5);

        doc0.addDocument(doc2);
        doc0.addDocument(doc3);
        doc3.addDocument(doc4);
        doc3.addDocument(doc5);

        System.out.println("Die Größe ist: "+doc0.getSize());
    }
}
