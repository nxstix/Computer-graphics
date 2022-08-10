package cgg.a05;
import cgtools.*;

// the hit class determinates a hit of a surface in a scene 
// ray parameter t, position of the hit with point x, normal vector n and the material the surface hit material
public record Hit (Double t, Point x, Direction n, Material material){
}