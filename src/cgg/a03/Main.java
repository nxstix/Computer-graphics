package cgg.a03;

import java.util.ArrayList;

import cgg.Scene.Image;
import cgtools.Color;
import cgtools.Point;

public class Main {

  public static void main(String[] args) {
    final int width = 1000;
    final int height = 600;

    // This class instance defines the contents of the image.
    Sphere sphere1 = new Sphere(new Point(3, 0, -5), 3, new Color(1, 0, 0.5));
    Sphere sphere2 = new Sphere(new Point(0, 0, -7), 3, new Color(0.5, 0, 0.5));
    Sphere sphere3 = new Sphere(new Point(-4, 0, -10), 3, new Color(0.5, 0, 1));

    Camera camera = new Camera(Math.PI/2, width, height);
    ArrayList<Sphere> spheres = new ArrayList<Sphere>();
    spheres.add(sphere1);
    spheres.add(sphere2);
    spheres.add(sphere3);
    Raytracer pic = new Raytracer(camera, spheres);
    
    // Creates an image and iterates over all pixel positions inside the image with a sample rate of 10 (supersampling).
    Image image = new Image(width, height);
    image.sample(pic, 10);

    // Write the image to disk.
    final String filename = "doc/a03-three-spheres.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
  }
}
