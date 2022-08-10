package cgg.Scene;

import java.util.concurrent.Callable;

import cgtools.*;

public record OnePixel (Sampler s, int n, double x, double y) implements Callable<Color>{

    public Color call() {
        Color color = new Color(0, 0, 0);
        for(int ix = 0; ix < n; ix++){
            for(int iy = 0; iy < n; iy++){
              double randomX = Random.random();
              double randomY = Random.random();
              double xs = x + (ix + randomX) / n; 
              double ys = y + (iy + randomY) / n;
              color = Vector.add(color, s.getColor(xs, ys));
            }
        }
        color = Vector.divide(color, n * n);
        return color;
    }
}
