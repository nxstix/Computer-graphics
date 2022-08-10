package cgg.Textures;

import cgtools.*;
import cgtools.Matrix;
import cgtools.Point;

public class TextureTransform implements Sampler{
    Sampler texture;
    Matrix transform;

    public TextureTransform(Sampler texture, Matrix transform){
        this.texture = texture;
        this.transform = transform;
    }

    public Color getColor(double u, double v) {
        Point uv = Matrix.multiply(transform, new Point(u, v, 0));
        return texture.getColor(uv.x(), uv.y());
    }
}