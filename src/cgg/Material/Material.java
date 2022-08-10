package cgg.Material;

import cgg.Scene.Hit;
import cgg.Scene.Ray;
import cgtools.Color;

public interface Material {
    Color emission(Ray ray, Hit hit);

    Color albedo(Ray ray, Hit hit);
    
    Ray ray(Ray ray, Hit hit);
}

