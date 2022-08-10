package cgg.Scene;

import cgtools.Color;
import cgtools.Sampler;

import static cgtools.Vector.*;

import cgg.Shapes.Group;

public class Raytracer implements Sampler{
    protected Group scene;
    protected Camera camera;
    
    public Raytracer(Camera camera, Group scene){
        this.scene = scene;
        this.camera = camera;
    }

    // raytracing => a ray is sent to every point 
    public Color getColor(double x, double y) {
        Color color = new Color(0, 0, 0);
        // generate ray through pixel
        Ray ray = camera.rayChangeCamera(x, y);
        // calculates radiance of ray
        Hit hit = scene.intersect(ray);
        if(hit != null){
            color = calculateRadiance(ray, scene, 5);
        }
        return color;
    }

    public Color calculateRadiance(Ray ray, Group scene, int depth){
        // check for maximum recursion depth
        if(depth == 0){
            return black;
        }
        // intersect ray with scene
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
            // return new Color(hit.u(), hit.v(), 0);
        } else {
            // absorbed, just emission
            return emission;
        }
    }
}
