package org.hbrs.se1.ws24.exercises.uebung9;

public class GraficDocument extends CoreDocument{

    private int size;
    private String domain;

    public GraficDocument(String domain) {
        this.domain = domain;
        this.size = 1200;
    }

    @Override
    public int getSize() {
        return size;
    }
}
