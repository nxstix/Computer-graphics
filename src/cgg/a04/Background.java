package cgg.a04;

import cgtools.*;
import static cgtools.Vector.*;

public class Background implements Shape{
    protected Color color;

    public Background(Color color){
        this.color = color;
    }

    public Hit intersect(Ray ray){
        Direction d = negate(ray.d);
        return new Hit(Double.POSITIVE_INFINITY, ray.pointAt(Double.POSITIVE_INFINITY), d, color);
    }
}
