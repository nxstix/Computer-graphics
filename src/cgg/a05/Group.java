package cgg.a05;

import java.util.ArrayList;

public class Group implements Shape {
    ArrayList<Shape> objects;
    
    Group(ArrayList<Shape> objects){
        this.objects = objects;
    }

    public Hit intersect(Ray ray) {
        Hit hit = null;
        double t = Double.POSITIVE_INFINITY;
        for (Shape shapes : objects){
            Hit hitShape = shapes.intersect(ray);
            if(hitShape != null){
                if(t >= hitShape.t()){
                    t = hitShape.t();
                    hit = hitShape;
                }
            } 
        }
        return hit;
    }
}