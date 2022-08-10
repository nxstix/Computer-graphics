package cgg.a04;

import cgtools.*;
import static cgtools.Vector.*;

public class Sphere implements Shape {
    protected Point m;
    protected double radius;
    protected Color color;

    Sphere(Point m, double radius, Color color){
        this.m = m;
        this.radius = radius;
        this.color = color;
    }
    
    public Hit intersect(Ray ray){
        Point x = ray.x0;
        Direction d = ray.d;
        // calculation if the sphere moves
        Direction shift = subtract(x, m);
        double a = dotProduct(d, d);
        double b = 2 * dotProduct(shift, d);
        double c = dotProduct(shift, shift) - radius * radius;
        double diskriminante = b * b - 4 * a * c;
        double t1 = (-b + Math.sqrt(diskriminante)) / 2 * a;
        double t2 = (-b - Math.sqrt(diskriminante)) / 2 * a;
        // compares the two rays to determin which one is closer to the camera
        if (t1 < t2 && ray.isValid(t1)){
           Direction d1 = normalize(divide(subtract(ray.pointAt(t1), m), radius));
           return new Hit(t1, ray.pointAt(t1), d1, color);
        } else if (t1 > t2 && ray.isValid(t2)){
            Direction d2 = normalize(divide(subtract(ray.pointAt(t2), m), radius));
            return new Hit(t2, ray.pointAt(t2), d2, color);
        }
        return null;
    }
}

