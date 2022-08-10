package cgg.Material;

import cgg.Scene.Hit;
import cgg.Scene.Ray;
import cgg.Textures.ConstTexture;
import cgg.Textures.Texture;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Random;
import cgtools.Sampler;

import static cgtools.Vector.*;

public class Metal implements Material{
    protected double mattnessFactor;
    protected Sampler albedo;

    public Metal(Color color, double mattnessFactor) {
        this.albedo = new ConstTexture(color);
        this.mattnessFactor = mattnessFactor;
    }

    public Metal(String file) {
        this.albedo = new Texture(file);
    }

    public Metal(Sampler albedo) {
        this.albedo = albedo;
    }

    public Color emission(Ray ray, Hit hit) {
        // emmission constant
        return black;
    }

    public Color albedo(Ray ray, Hit hit) {
        // albedo present
        return albedo.getColor(hit.u(), hit.v());
    }

    public Ray ray(Ray ray, Hit hit) {
        Point x0Point = hit.x();
        // direction of secondary ray => r = d - 2(n * d)n
        Direction direction = subtract(ray.d, (multiply(2 * (dotProduct(hit.n(), ray.d)), hit.n())));
        Direction directionRandom = new Direction(Random.random(), Random.random(), Random.random());
        double d = length(directionRandom);
        double radius = 1;
        double tmin = 0.0004;
        if(d <= radius){
            // in
            // new dreiction w/ scattering && factor of how matt the shape should be
            Direction directionWStreuung = add(direction, multiply(directionRandom, mattnessFactor));
            Ray reflectedRay = new Ray(x0Point, directionWStreuung, Double.POSITIVE_INFINITY, tmin);
            return reflectedRay;
        } 
        // out => find new ray with right direction 
        return ray(ray, hit);
    }
}
