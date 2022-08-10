package cgg.a03;

import java.util.ArrayList;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Sampler;
import static cgtools.Vector.*;

public class Raytracer implements Sampler{
    protected ArrayList<Sphere> sphere;
    protected Camera camera;
    
    Raytracer(Camera camera, ArrayList<Sphere> sphere){
        this.sphere = sphere;
        this.camera = camera;
    }

    // raytracing => a ray is sent to every point 
    public Color getColor(double x, double y) {
        Color color = new Color(0, 0, 0);
        // generated ray through pixel
        Ray ray = camera.ray(x, y);
        double treffer = Double.POSITIVE_INFINITY;
        // finds closest intersection within scene and camera
        for (Sphere sphereen : sphere) {
            Hit hit = sphereen.intersect(ray);
            if (hit != null){
                if(treffer > hit.t()){
                    treffer = hit.t();
                    color = shade(hit.n(), sphereen.color);
                }
            }
        }
        return color;
    }

    // calculate light intensity
    static Color shade(Direction normal, Color color) {
        Direction lightDir = normalize(direction(1, 1, 0.5));
        Color ambient = multiply(0.1, color);
        Color diffuse = multiply(0.9 * Math.max(0, dotProduct(lightDir, normal)), color);
        return add(ambient, diffuse);
    }

}
