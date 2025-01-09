package org.hbrs.se1.ws24.exercises.uebung10;

public class MyPoint {
    private double x;
    private double y;

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MyPoint) {
            return ((MyPoint) obj).x == x && ((MyPoint) obj).y == y;
        }
        return false;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
