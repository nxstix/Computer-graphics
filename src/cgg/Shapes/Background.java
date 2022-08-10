package cgg.Shapes;

import cgtools.*;
import static cgtools.Vector.*;

import cgg.Material.Material;
import cgg.Scene.Hit;
import cgg.Scene.Ray;

public class Background implements Shape{
    protected Material materail;

    public Background(Material materail){
        this.materail = materail;
    }

    public Hit intersect(Ray ray){
        Direction d = negate(ray.d);
        double inclination = Math.acos(ray.d.y());
        double azimuth = Math.PI + Math.atan2(ray.d.x(), ray.d.z());
        double u = azimuth / (2 * Math.PI);
        double v = inclination / Math.PI;
        return new Hit(Double.POSITIVE_INFINITY, ray.pointAt(Double.POSITIVE_INFINITY), d, materail, u, v);
    }
}
