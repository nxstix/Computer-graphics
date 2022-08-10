package cgg.Material;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Random;
import cgtools.Sampler;

import static cgtools.Vector.*;

import cgg.Scene.Hit;
import cgg.Scene.Ray;
import cgg.Textures.ConstTexture;
import cgg.Textures.Texture;

public class Diffuse implements Material{
    protected Sampler albedo;
    
    public Diffuse(Color color) {
        this.albedo = new ConstTexture(color);
    }

    public Diffuse(Sampler albedo) {
        this.albedo = albedo;
    }

    public Diffuse(String file) {
        this.albedo = new Texture(file);
    }

    public Color emission(Ray ray, Hit hit) {
        // emmission constant / black
        return black;
    }

    public Color albedo(Ray ray, Hit hit) {
        return albedo.getColor(hit.u(), hit.v());
    }

    public Ray ray(Ray ray, Hit hit) {
        // ray with random direction 
        double tmin = 0.0004;
        Point x0Point = hit.x();
        Direction direction = new Direction(Random.random(), Random.random(), Random.random());
        double d = length(direction);
        double radius = 1;
        if(d <= radius){
            // in
            Direction newD = normalize(add(direction, hit.n()));
            Ray stochastisches = new Ray(x0Point, newD, Double.POSITIVE_INFINITY, tmin);
            return stochastisches;
        } 
        // out => find new ray with right direction 
        return ray(ray, hit);
    }
}