package cgg.a05;

import cgtools.Color;

public interface Material {
    Color emission(Ray ray, Hit hit);

    Color albedo(Ray ray, Hit hit);
    
    Ray ray(Ray ray, Hit hit);
}

