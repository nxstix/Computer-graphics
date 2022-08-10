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

public class Glass implements Material{
    protected double n1;
    protected double n2;
    protected Sampler albedo;

    // density: air = 1.0; water = 1.3; glass = 1.5
    public Glass(Color color, double n1, double n2) {
        this.albedo = new ConstTexture(color);
        this.n1 = n1;
        this.n2 = n2;
    }

    public Glass(String file, double n1, double n2) {
        this.albedo = new Texture(file);
        this.n1 = n1;
        this.n2 = n2;
    }

    public Glass(Sampler albedo, double n1, double n2) {
        this.albedo = albedo;
        this.n1 = n1;
        this.n2 = n2;
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
        // Transparent => n1 * sinø1 = n2 * sinø2
        Point x0Point = hit.x();
        Direction direction = hit.n();
        double n1temp = n1;
        double n2temp = n2;
        double tmin = 0.0004;

        // Calculation of the direction of secondary ray that gets broken
        // check if ray comes from inside
        if(dotProduct(hit.n(), ray.d) > 0){
            direction = multiply(-1, direction);
            double tempN = n1;
            n1temp = n2temp;
            n2temp = tempN;
        }

        // Snell's Law => direction change when passing through another medium
        // c = -n * d
        double c = dotProduct(multiply(-1, hit.n()), ray.d);
        // r = n1 / n2
        double r = n1temp / n2temp;
        // direction of broken ray => dt = rd + (rc - sqr.( "1 - r^2(1-c^2" )) * n
        double i = 1 - r * r * (1 - c * c);

        // schlicks approximation
        double r0 = Math.pow(((n1temp - n2temp) / (n1temp + n2temp)), 2);
        double schlick = r0 + (1 - r0) * Math.pow(1 + dotProduct(hit.n(), ray.d), 5);
        
        // checking critial point
        if(i >= 0 && Random.random() > schlick){
            // scattered ray
            Direction dt = add(multiply(r, ray.d), multiply((r * c - Math.sqrt(i)), hit.n()));
            Ray scatteredRay = new Ray(x0Point, dt, Double.POSITIVE_INFINITY, tmin);
            return scatteredRay;
        } 
        // total reflection = over critiical point => n1 / n2 * sinø1 > 1
        Direction dMirror = subtract(ray.d, multiply(2 * dotProduct(hit.n(), ray.d), hit.n()));
        Ray mirrorRa = new Ray(x0Point, dMirror, Double.POSITIVE_INFINITY, tmin);
        return mirrorRa;
    }
}