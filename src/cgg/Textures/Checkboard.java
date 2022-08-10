package cgg.Textures;

import cgtools.Color;
import cgtools.Sampler;

public class Checkboard implements Sampler {
    int n;
    Color c1;
    Color c2;

    public Checkboard (int n, Color c1, Color c2){
        this.n = n;
        this.c1 = c1;
        this.c2 = c2;
    }

    public Color getColor(double u, double v) {
    int ui = (int)((u % 1) * n);
    int vi = (int)((v % 1) * n);
    if ((ui + vi) % 2 == 0)
        return c1;
    else
        return c2;
};
    
}
