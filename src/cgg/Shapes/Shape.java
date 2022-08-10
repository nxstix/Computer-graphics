package cgg.Shapes;

import cgg.Scene.Hit;
import cgg.Scene.Ray;

public interface Shape {
    
    Hit intersect(Ray ray);
}
