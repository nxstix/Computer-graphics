package cgg.Material;

import cgg.Scene.Hit;
import cgg.Scene.Ray;
import cgg.Textures.ConstTexture;
import cgg.Textures.Texture;
import cgtools.Color;
import cgtools.Sampler;

public class ConstColor implements Material{
    // background/light source (emits light) 
    protected Sampler albedo;

    public ConstColor(Color color) {
        this.albedo = new ConstTexture(color);
    }

    public ConstColor(Sampler albedo) {
        this.albedo = albedo;
    }

    public ConstColor(String file) {
        this.albedo = new Texture(file);
    }

    public Color emission(Ray ray, Hit hit) {
        // color of background
        return albedo.getColor(hit.u(), hit.v());
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