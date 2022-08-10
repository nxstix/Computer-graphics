package cgg.Scene;

import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;

public class Transformation {
    Matrix toWorld;
    // inverse
    Matrix toLocal;
    // transponierte
    Matrix toWorldN;
    
    public Transformation(Matrix toWorld){
        this.toWorld = toWorld;
        this.toLocal = Matrix.invert(toWorld);
        this.toWorldN = Matrix.transpose(toLocal);
    }

    public Ray transform(Ray ray){
        // M^-1
        Point rP = Matrix.multiply(toLocal, ray.x0);
        Direction rD = Matrix.multiply(toLocal, ray.d);
        return new Ray(rP, rD, ray.tmax, ray.tmin);
    }

    public Hit transform(Hit hit){
        // M
        Point dP = Matrix.multiply(toWorld, hit.x());
        Direction dD = Matrix.multiply(toWorldN, hit.n());
        return new Hit(hit.t(), dP, dD, hit.material(), hit.u(), hit.v());
    }
}