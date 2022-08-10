package cgg.a05;

import cgtools.Color;
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
        // generate ray through pixel
        Ray ray = camera.ray(x, y);
        // calculates radiance of ray
        color = calculateRadiance(ray, shapes, 5);
        return color;
    }

    public Color calculateRadiance(Ray ray, Group scene, int depth){
        // check for maximum recursion depth
        if(depth == 0){
            return black;
        }
        // intersects ray with scene
        Hit hit = scene.intersect(ray);
        // emission for point
        Color emission = hit.material().emission(ray, hit);
        // albedo for point
        Color albedo = hit.material().albedo(ray, hit);
        // ray for this point
        Ray rayScattered = hit.material().ray(ray, hit);
        // unknown material at hit point 
        if(rayScattered != null){
            // emission and reflection are combined
            return add(emission, multiply(albedo, calculateRadiance(rayScattered, scene, depth - 1)));
        } else {
            // absorbed, just emission
            return emission;
        }
    }
}
