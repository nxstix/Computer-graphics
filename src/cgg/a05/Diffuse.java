package cgg.a05;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Random;

import static cgtools.Vector.*;

public class Diffuse implements Material{
    protected Color color;

    Diffuse(Color color) {
        this.color = color;
    }

    public Color emission(Ray r, Hit h) {
        // emmission constant / black
        return black;
    }

    public Color albedo(Ray r, Hit h) {
        return color;
    }

    public Ray ray(Ray r, Hit h) {
        // ray with random direction 
        double tmin = 0.0004;
        Point x0Point = h.x();
        Direction direction = new Direction(Random.random(), Random.random(), Random.random());
        double d = length(direction);
        double radius = 1;
        if(d <= radius){
            // in
            Direction newD = normalize(add(direction, h.n()));
            Ray stochastisches = new Ray(x0Point, newD, Double.POSITIVE_INFINITY, tmin);
            return stochastisches;
        } 
        // out => find new ray with right direction 
        return ray(r, h);
    }
}