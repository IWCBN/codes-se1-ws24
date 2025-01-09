package org.hbrs.se1.ws24.exercises.uebung10;


public class MyPrettyRectangle {
        private MyPoint p1;
        private MyPoint p2;

    public MyPrettyRectangle(double x1, double y1, double x2, double y2) {
        p1 = new MyPoint(x1, y1);
        p2 = new MyPoint(x2, y2);
        if(x1 > x2) {
            System.out.println("Keine gültige Eingabe (x1 > x2)");
        } else if(y1 > y2) {
            System.out.println("Keine gültige Eingabe (y1 > y2");
        }
    }

    private boolean hasArea() {
        return p1.getX() != p2.getX() || p1.getY() != p2.getY();
    }

    @Override
    public boolean equals(Object obj) {
        return (p1.equals(p2));
    }

    public boolean contains(MyPrettyRectangle right) {
        return false;
    }

    public MyPoint getCenter() {
        double tmpX;
        double tmpY;
        MyPoint center;

        if(hasArea()) {
            tmpX = (p1.getX() != p2.getX() ? ((p2.getX() - p1.getX())/2 + p1.getX()) : p1.getX());
            tmpY = (p1.getY() != p2.getY() ? ((p2.getY() - p1.getY())/2 + p1.getY()) : p1.getY());
            center = new MyPoint(tmpX, tmpY);
        } else {
            return p1;
        }

        return center;
    }

    public double getArea() {
        return (p2.getX() - p1.getX()) * (p2.getY() - p1.getY());
    }

    public double getPerimeter() {
        return ((p2.getX() - p1.getX())*2 + (p2.getY() - p1.getY())*2);
    }

    public MyPrettyRectangle boundingBox(MyPrettyRectangle[] a) {

    }

    public String toString() {
        return "Rectangle [p1=" + p1.toString() + ", p2=" + p2.toString() + "]";
    }
}
