package cgg.Shapes;

import java.util.ArrayList;

import cgg.Scene.Hit;
import cgg.Scene.Ray;
import cgg.Scene.Transformation;
import cgtools.Matrix;

public class Group implements Shape {
    ArrayList<Shape> objects;
    Transformation t;
    
    public Group(ArrayList<Shape> objects){
        this.objects = objects;
        this.t = new Transformation(Matrix.identity);
    }

    public Group(ArrayList<Shape> objects, Transformation t){
        this.objects = objects;
        this.t = t;
    }

    // public Hit intersect(Ray ray) {
    //     Hit hit = null;
    //     double t = Double.POSITIVE_INFINITY;
    //     for (Shape objects : objects){
    //         Hit hitShape = objects.intersect(ray);
    //         if(hitShape != null){
    //             if(t >= hitShape.t()){
    //                 t = hitShape.t();
    //                 hit = hitShape;
    //             }
    //         } 
    //     }
    //     return hit;
    // }

    public Hit intersect(Ray ray) {
        Ray rayT = t.transform(ray);
        Hit hit = null;
        double inf = Double.POSITIVE_INFINITY;
        for (Shape objects : objects){
            Hit hitShape = objects.intersect(rayT);
            if(hitShape != null){
                if(inf >= hitShape.t()){
                    inf = hitShape.t();
                    hit = hitShape;
                }
            } 
        }
        if(hit != null){
            hit = t.transform(hit);
        }
        return hit;
    }
}