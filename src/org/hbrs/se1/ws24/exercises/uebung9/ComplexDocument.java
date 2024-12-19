package org.hbrs.se1.ws24.exercises.uebung9;

import java.awt.*;
import java.util.List;

public class ComplexDocument implements Document{
    private int id;
    private List<Document> documents;

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getSize() {
        int size = 0;
        for(Document document : documents){
            size += document.getSize();
        }
        return size;
    }

    public void addDocument(Document document) {
        documents.add(document);
    }

    public void removeDocument(Document document) {
        documents.remove(document);
    }
}
