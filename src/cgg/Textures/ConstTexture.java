package cgg.Textures;

import cgtools.Color;
import cgtools.Sampler;

public class ConstTexture implements Sampler{
    protected Color color;

    public ConstTexture(Color color) {
        this.color = color;
    }

    public Color getColor(double u, double v) {
        return color;
    }
}