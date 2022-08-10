package cgg.Textures;

import cgtools.Color;
import cgtools.Sampler;

public class PolkaDots implements Sampler {
    double n;
    Color c1;
    Color c2;
    double radius;

    public PolkaDots (double n, Color c1, Color c2, double radius){
        this.n = n;
        this.c1 = c1;
        this.c2 = c2;
        this.radius = radius;
    }

    public Color getColor(double x, double y) {
        double pointX = x % n;
        double pointY = y % n;
        double dx = pointX - n / 2;
        double dy = pointY - n / 2;
        double d = Math.sqrt(dx * dx + dy * dy);
        if(radius > d){
            // in
            return c1;
        } 
        // out
        return c2;
    }

}