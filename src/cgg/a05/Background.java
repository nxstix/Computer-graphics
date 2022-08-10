package cgg.a05;

import cgtools.*;
import static cgtools.Vector.*;

public class Background implements Shape{
    protected Material materail;

    public Background(Material materail){
        this.materail = materail;
    }

    public Hit intersect(Ray ray){
        Direction d = negate(ray.d);
        return new Hit(Double.POSITIVE_INFINITY, ray.pointAt(Double.POSITIVE_INFINITY), d, materail);
    }
}
