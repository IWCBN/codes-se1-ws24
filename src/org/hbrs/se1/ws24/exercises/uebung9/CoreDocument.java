package org.hbrs.se1.ws24.exercises.uebung9;

public abstract class CoreDocument implements Document {
    private int id;


    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }

    public abstract int getSize();

}
