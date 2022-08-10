package cgg.a04;

import cgtools.*;
import static cgtools.Vector.*;

// camera with adjustable specific angle and image size, returning rays
public class Camera  {
    protected Double angle;
    protected int w;
    protected int h;

    public Camera(Double angle, int w, int h){
        this.angle = angle;
        this.w = w;
        this.h = h;
    }

    // calculated ray to the given sampling point
    public Ray ray(double xp, double yp){
        // normalized direction of ray d -> from point (xp, yp) of the scene
        Direction d = normalize(direction(xp - w/2, h/2 - yp, - (w/2) / Math.tan(angle/2))); 
        Point point = new Point(0, 0, 0);
        Ray ray =  new Ray(point, d, Double.POSITIVE_INFINITY, 0.0);
       return ray;
    } 
}