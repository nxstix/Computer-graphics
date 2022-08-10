package cgg.a02;
import java.util.ArrayList;

import cgtools.*;

public class ColoredDiscs implements Sampler {
    protected ArrayList<Disc> discs;

    ColoredDiscs(int width, int height, int size){
        discs = new ArrayList<Disc>();
        for(int i = 0; i < size; i++){
            double x = Random.random() * width;
            double y = Random.random() * height;
            double radius = Random.random() * 350;
            Color color = new Color(Random.random(), Random.random(), Random.random());
            discs.add(new Disc(x, y, radius, color));
        }
    }

    public Color getColor(double x, double y) {
        Color color = new Color(0, 0, 0);
        // sorted from largest to smallest
        discs.sort((a, b) -> Double.compare(a.radius, b.radius));
        double tempR = 100;
        for (Disc disc : discs) {
            if(disc.isPointInDisc(x, y) && disc.radius < tempR){
                // inside the indevidual discs
                tempR = disc.radius;
                return disc.color;
            }
        }
        return color;
    }
}
