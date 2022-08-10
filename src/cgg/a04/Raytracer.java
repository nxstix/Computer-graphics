package cgg.a04;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Sampler;
import static cgtools.Vector.*;

public class Raytracer implements Sampler{
    protected Group shapes;
    protected Camera camera;
    
    Raytracer(Camera camera, Group shapes){
        this.shapes = shapes;
        this.camera = camera;
    }

    // raytracing => a ray is sent to every point 
    public Color getColor(double x, double y) {
        Color color = new Color(0, 0, 0);
        // generated ray through pixel
        Ray ray = camera.ray(x, y);
        // finds closest intersection within scene and camera
        Hit hit = shapes.intersect(ray);
        if (hit != null){
            color = shade(hit.n(), hit.c());
        }
        return color;
    }
    
    // Calculate light intensity
    static Color shade(Direction normal, Color color) {
        Direction lightDir = normalize(direction(1, 1, 0.5));
        Color ambient = multiply(0.1, color);
        Color diffuse = multiply(0.9 * Math.max(0, dotProduct(lightDir, normal)), color);
        return add(ambient, diffuse);
    }
}
