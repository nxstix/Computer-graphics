package cgg.Scene;

import cgtools.*;
import static cgtools.Vector.*;

public class Ray {
    public Point x0;
    public Direction d;
    public Double tmax;
    public Double tmin;
    
    // strahl
    public Ray(Point x0, Direction d, Double tmax, Double tmin){
        this.x0 = x0;
        this.d = d;
        this.tmax = tmax;
        this.tmin = tmin;
    }

    // direction of t => calculated from point x0
    public Point pointAt(double t){
        Point xt = add(multiply(t, d), x0);
        return xt;
    }

    // checks if t is inbetween tmin and tmax
    public boolean isValid(double t){
        if(tmin <= t && tmax >= t){
            return true;
        }
        return false;
    }
}
 