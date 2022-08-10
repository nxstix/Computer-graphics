package cgg.Scene;
import cgg.Material.Material;
import cgtools.*;

// the hit class determinates a hit of a surface in a scene 
// ray parameter t, position of the hit with point x, normal vector n and the color the surface hit c
// douvle u and v is for the tranformation of textures on surfaces
public record Hit (Double t, Point x, Direction n, Material material, double u, double v){
}