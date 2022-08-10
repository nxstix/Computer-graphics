package cgg.a01;
import cgtools.*;

public record Circles(Color color) implements Sampler {

    // Returns the color for the given position.
    public Color getColor(double x, double y) {
        double kx= 240.0;
        double ky = 135.0;
        double radius = 100;
        double dx = x - kx;
        double dy = y - ky;
        double d = Math.sqrt(dx * dx + dy * dy);
        if(radius > d){
            // color inside the circle
            return color;
        } 
        // color outside of the circle
        return  new Color(0, 0, 0);
    }
}
