package cgg.Shapes;

import cgg.Material.Material;
import cgg.Scene.*;
import cgtools.*;

import static cgtools.Vector.*;

public class Tube implements Shape {
    Point m;
    Material material;
    double radius;
    double height;

    public Tube(Point m, Material material, double radius, double height){
        this.m = m;
        this.material = material;
        this.radius = radius; 
        this.height = height;
    }

    // cl.cam.ac.uk. "Ray tracing primitives" cl.cam.ac.uk. 28.05.2022, https://www.cl.cam.ac.uk/teaching/1999/AGraphHCI/SMAG/node2.html#eqn:rectcyl.
    // x^2 + y^2 = r^2
    public Hit intersect(Ray ray) {
        Point x = ray.x0;
        Direction d = ray.d;
        // verschiebung
        Direction shift = subtract(x, m);

        // // abc equation
        double a = (d.x() * d.x()) + (d.z() * d.z());
        double b = (2 * shift.x() * d.x()) + (2 * shift.z() * d.z());
        double c = (shift.x() * shift.x()) + (shift.z() * shift.z()) - (radius * radius);
        double diskriminante = b * b - 4 * a * c;

        // check which point is nearer to camera
        double t1 = (-b + Math.sqrt(diskriminante)) / (2 * a);
        double t2 = (-b - Math.sqrt(diskriminante)) / (2 * a);

        Point endTop = new Point(m.x(), m.y() + height, m.z());

        if (t1 > t2 && ray.isValid(t2) && ray.pointAt(t2).y() <= endTop.y() && ray.pointAt(t2).y() >= m.y()){
            Direction d2 = normalize(divide(subtract(ray.pointAt(t2), m), radius));
            double inclination = Math.acos(d2.y());
            double azimuth = Math.PI + Math.atan2(d2.x(), d2.z());
            double u = azimuth / (2 * Math.PI);
            double v = inclination / Math.PI;
            return new Hit(t2, ray.pointAt(t2), d2, material, u, v);
        } else if (t1 < t2 && ray.isValid(t1) && ray.pointAt(t1).y() <= endTop.y() && ray.pointAt(t1).y() >= m.y()){
            Direction d1 = normalize(divide(subtract(ray.pointAt(t1), m), radius));
            double inclination = Math.acos(d1.y());
            double azimuth = Math.PI + Math.atan2(d1.x(), d1.z());
            double u = azimuth / (2 * Math.PI);
            double v = inclination / Math.PI;
            return new Hit(t1, ray.pointAt(t1), d1, material, u, v);
        }
        return null;
    }   
}