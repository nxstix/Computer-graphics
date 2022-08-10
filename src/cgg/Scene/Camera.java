package cgg.Scene;

import cgtools.*;

import cgtools.Matrix;
import static cgtools.Vector.*;

// camera with adjustable specific angleTilt and image size, returning rays
public class Camera  {
    protected Double angleLens;
    protected int w;
    protected int h;
    protected double angleTilt;
    protected Direction axisT;
    protected Direction axisR;

    public Camera(Double angleLens, int w, int h, double angleTilt, Direction axisT, Direction axisR){
        this.angleLens = angleLens;
        this.w = w;
        this.h = h;
        this.angleTilt = angleTilt;
        this.axisT = axisT;
        this.axisR = axisR;
    }

    
    // calculated ray to the given sampling point
    // public Ray ray(double xp, double yp){
    //     // normalized direction of ray d -> from point (xp, yp) of the scene
    //     Direction d = normalize(direction(xp - w/2, h/2 - yp, - (w/2) / Math.tan(angleLens/2))); 
    //     Point point = new Point(0, 0, 0);
    //     Ray ray =  new Ray(point, d, Double.POSITIVE_INFINITY, 0.0);
    //    return ray;
    // } 

    // changed position of camera
    public Ray rayChangeCamera(double xp, double yp){
         // normalized direction of ray d -> from point (xp, yp) of the scene
        Direction d = normalize(direction(xp - w/2, - (yp - h/2), (- (w/2) / Math.tan(angleLens/2)))); 
        Point point = new Point(0, 0, 0);
        // r = (v * x0) + (t * v * d)
        Matrix matrixT = Matrix.translation(axisT.x(), axisT.y(), axisT.z());
        Matrix matrixR = Matrix.rotation(axisR, angleTilt);
        Point newPoint = Matrix.multiply(matrixT, point);
        Direction newDirection = Matrix.multiply(matrixR, d);
        Ray ray =  new Ray(newPoint, newDirection, Double.POSITIVE_INFINITY, 0.0);
        return ray;
    } 
}
