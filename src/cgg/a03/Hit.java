package cgg.a03;
import cgtools.*;

// the hit class determinates a hit of a surface in a scene 
// ray parameter t, position of the hit with point x, normal vector n and the color of the surface hit c
public record Hit (Double t, Point x, Direction n, Color c){
}