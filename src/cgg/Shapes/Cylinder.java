package cgg.Shapes;

import cgg.Material.Material;
import cgg.Scene.Hit;
import cgg.Scene.Ray;
import cgtools.Direction;
import cgtools.Point;

public class Cylinder implements Shape{
    Point m;
    Direction d;
    Material material;
    double radius;
    double height;
    Group cylinder;
    Plane top;
    Plane bottom;
    Tube tube;

    public Cylinder(Point m, Direction d, Material material, double radius, double height){
        this.m = m;
        this.d = d;
        this.material = material;
        this.radius = radius; 
        this.height = height;
        // top
        Point endTop = new Point(m.x(), m.y() + height, m.z());
        this.top = new Plane(endTop, d, material, radius);
        this.bottom = new Plane(m, d, material, radius);
        this.tube = new Tube(m, material, radius, height);
    }

    public Cylinder(Point m, Direction d, Material material, double radius, double height, int w, int h){
        this.m = m;
        this.d = d;
        this.material = material;
        this.radius = radius; 
        this.height = height;
        // top
        Point endTop = new Point(m.x(), m.y() + height, m.z());
        this.top = new Plane(endTop, d, material, radius, w, h);
        this.bottom = new Plane(m, d, material, radius, w, h);
        this.tube = new Tube(m, material, radius, height);
    }

    public Hit intersect(Ray ray) {
        double topT = Double.POSITIVE_INFINITY;
        double bottomT = Double.POSITIVE_INFINITY;
        double tubeT = Double.POSITIVE_INFINITY;
        if(top.intersect(ray) != null){
            topT = top.intersect(ray).t();
        }

        if(bottom.intersect(ray) != null){
            bottomT = bottom.intersect(ray).t();
        }

        if(tube.intersect(ray) != null){
            tubeT = tube.intersect(ray).t();
        }

        if(topT < tubeT && topT < bottomT){
            return top.intersect(ray);
        } else if(tubeT < topT && tubeT < bottomT){
            return tube.intersect(ray);
        } else if(bottomT < topT && bottomT < tubeT){
            return bottom.intersect(ray);
        }
        return null;
    }
}
