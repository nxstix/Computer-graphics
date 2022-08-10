package cgg.a05;

import cgtools.Color;

public class ConstColor implements Material{
    // background/light source (emits light) 
    protected Color color;

    public ConstColor(Color color) {
        this.color = color;
    }

    public Color emission(Ray ray, Hit hit) {
        // color of background
        return color;
    }

    public Color albedo(Ray ray, Hit hit) {
        // no scattering, no albedo
        return null;
    }

    public Ray ray(Ray ray, Hit hit) {
        // no scattering
        return null;
    }
}