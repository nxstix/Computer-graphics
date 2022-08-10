package cgg.Shapes;

import cgg.Material.Material;
import cgg.Scene.*;
import cgtools.*;

public class Plane implements Shape{
    Point centerPoint;
    Direction d;
    Material material;
    double radius;
    int w;
    int h;

    public Plane(Point centerPoint, Direction d, Material material, double radius){
        this.radius = radius; 
        this.centerPoint = centerPoint;
        this.d = d;
        this.material = material;
    }

    public Plane(Point centerPoint, Direction d, Material material){
        this.radius = Double.POSITIVE_INFINITY; 
        this.centerPoint = centerPoint;
        this.d = d;
        this.material = material;
    }

    public Plane(Point centerPoint, Direction d, Material material, double radius, int w, int h){
        this.radius = radius; 
        this.centerPoint = centerPoint;
        this.d = d;
        this.material = material;
        this.w = w;
        this.h = h;
    }

    public Plane(Point centerPoint, Direction d, Material material, int w, int h){
        this.radius = Double.POSITIVE_INFINITY; 
        this.centerPoint = centerPoint;
        this.d = d;
        this.material = material;
        this.w = w;
        this.h = h;
    }

    public Hit intersect(Ray ray) {
        // center point on plane (x - p)
        Direction dd = Vector.subtract(centerPoint, ray.x0);
        // plane equation = dot product of (x - p) * normalized vector   
        double plane = Vector.dotProduct(d, Vector.normalize(d));
        double rayD = Vector.dotProduct(ray.d, Vector.normalize(d));
        // ray of plane 
        double rayP = plane / rayD;
        // absolute value => length of direction
        double length = Vector.length(Vector.subtract(ray.pointAt(rayP), centerPoint));
        // iif absolute value < radius && ray is between tmin and tmax
        if(length < radius && ray.isValid(rayP)){ 
            Point uvPoint = ray.pointAt(rayP);
            double u = uvPoint.x() / w + 0.5;
            double v = uvPoint.z() / h + 0.5;
            return new Hit(rayP, uvPoint, dd, material, u, v); 
        }
        return null;
    }
}
