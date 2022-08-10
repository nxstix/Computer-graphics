package cgg.a02;
import cgtools.*;

public class Disc{
    protected double mX;
    protected double mY;
    protected double radius;
    protected Color color;

    Disc(double mX, double mY, double radius, Color color){
        this.mX = mX;
        this.mY = mY;
        this.radius = radius;
        this.color = color;
    }

    // checks if a point (x, y) is in the disc or not.
    public boolean isPointInDisc(double x, double y){
        double dx = mX - x;
        double dy = mY - y;
        double d = Math.sqrt(dx * dx + dy * dy);
        if(radius > d){
            return true;
        }
       return false;
    }
}
