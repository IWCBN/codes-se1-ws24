package org.hbrs.se1.ws24.exercises.uebung10;


import static java.lang.Math.abs;

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

        if(hasNoArea()){
            System.out.println("Das Rechteck hat keinen Flächeninhalt");
        }
    }

    private boolean hasNoArea() {
        return p1.getX() == p2.getX() || p1.getY() == p2.getY();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MyPrettyRectangle) {
            MyPrettyRectangle other = (MyPrettyRectangle) obj;
            return (this.p1.equals(other.p1) && this.p2.equals(other.p2));
        }
        return false;
    }

    public boolean contains(MyPrettyRectangle small) {
        if(this.p1.getX() <= small.p1.getX() && this.p1.getY() <= small.p1.getY()) {
            if(this.p2.getX() >= small.p2.getX() && this.p2.getY() >= small.p2.getY()) {
                return true;
            }
        }
        return false;
    }

    public MyPoint getCenter() {
        double tmpX;
        double tmpY;
        MyPoint center;

        tmpX = (p2.getX() - p1.getX())/2 + p1.getX();
        tmpY = (p2.getY() - p1.getY())/2 + p1.getY();

        center = new MyPoint(tmpX, tmpY);

        return center;
    }

    public double getArea() {
        return abs((p2.getX() - p1.getX()) * (p2.getY() - p1.getY()));
    }

    public double getPerimeter() {
        return (abs((p2.getX() - p1.getX())*2) + abs((p2.getY() - p1.getY())*2));
    }

    public static MyPrettyRectangle boundingBox(MyPrettyRectangle[] a) {
        if (a == null){
            throw new IllegalArgumentException("");
        }
        if(a.length == 0){
            return new MyPrettyRectangle(0,0,0,0);
        }
        MyPoint BoxP1 = new MyPoint(a[0].p1.getX(), a[0].p1.getY());
        MyPoint BoxP2 = new MyPoint(a[0].p2.getX(), a[0].p2.getY());

        for(int i = 1; i < a.length; i++){
            if(BoxP1.getX()>a[i].p1.getX()){
                BoxP1.setX(a[i].p1.getX());
            }
            if(BoxP1.getY()>a[i].p1.getY()){
                BoxP1.setY(a[i].p1.getY());
            }
            if(BoxP2.getX()<a[i].p2.getX()){
                BoxP2.setX(a[i].p2.getX());
            }
            if(BoxP2.getY()<a[i].p2.getY()){
                BoxP2.setY(a[i].p2.getY());
            }
        }

        return new MyPrettyRectangle(BoxP1.getX(), BoxP1.getY(), BoxP2.getX(), BoxP2.getY());
    }

    public String toString() {
        return "Rectangle [p1=" + p1.toString() + ", p2=" + p2.toString() + "]";
    }
}
