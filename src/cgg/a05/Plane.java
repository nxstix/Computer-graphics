package cgg.a05;

import cgtools.*;

public class Plane implements Shape{
    Point centerPoint;
    Direction nV;
    Material material;
    double radius;

    // plane with specific radius
    Plane(Point centerPoint, Direction nV, Material material, double radius){
        this.radius = radius; 
        this.centerPoint = centerPoint;
        this.nV = nV;
        this.material = material;
    }

    // standard plane
     Plane(Point centerPoint, Direction nV, Material material){
        this.radius = Double.POSITIVE_INFINITY; 
        this.centerPoint = centerPoint;
        this.nV = nV;
        this.material = material;
    }

    public Hit intersect(Ray ray) {
        // center point on plane (x - p)
        Direction d = Vector.subtract(centerPoint, ray.x0);
        // plane equation = dot product of (x - p) * normalized vector   
        double plane = Vector.dotProduct(d, Vector.normalize(nV));
        double rayD = Vector.dotProduct(ray.d, Vector.normalize(nV));
        // ray of plane 
        double rayP = plane / rayD;
        // absolute value => length of direction
        double length = Vector.length(Vector.subtract(ray.pointAt(rayP), centerPoint));
        // iif absolute value < radius && ray is between tmin and tmax
        if(length < radius && ray.isValid(rayP)){ 
            return new Hit(rayP, ray.pointAt(rayP), nV, material); 
        }
        return null;
    }
}
