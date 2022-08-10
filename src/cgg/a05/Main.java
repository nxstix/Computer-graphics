package cgg.a05;

import java.util.ArrayList;

import cgg.Scene.Image;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Random;

public class Main {

  public static void main(String[] args) {
    final int width = 1000;
    final int height = 700;
    Random.seed(4);

    // This class instance defines the contents of the image.
    Camera camera = new Camera(Math.PI / 3, width, height);
    Shape backgroundS3 = new Background(new ConstColor(new Color(1, 1, 1)));
    Shape ground = new Plane(new Point(0.0, -1, 0.0), new Direction(0, 1, 0), new Diffuse(new Color(0.95, 0.95, 0.95)), 15);

    Shape sphere1 = new Sphere(new Point(0, Random.random() - 0.5, - 6 * Random.random()), 1, new Diffuse(new Color(1, 0.7, 0.3)));
    Shape sphere2 = new Sphere(new Point(2, Random.random(), - 6 * Random.random()), 0.8, new Diffuse(new Color(1, 0.7, 0.2)));
    Shape sphere3 = new Sphere(new Point(-2, Random.random() - 0.5, - 6 * Random.random()), 0.8, new Diffuse(new Color(1, 0.5, 0.6)));
    Shape sphere4 = new Sphere(new Point(-1, Random.random(), - 7 * Random.random()), 0.5, new Diffuse(new Color(9, 0.5, 0.4)));
    Shape sphere5 = new Sphere(new Point(-2, - 0.5, - 7), 0.7, new Diffuse(new Color(1, 0.9, 0.3)));
    Shape sphere6 = new Sphere(new Point(1.5, Random.random(), - 7 * Random.random()), 0.5, new Diffuse(new Color(1, 0.7, 0.9)));
    Shape sphere7 = new Sphere(new Point(1, - 0.7, - 3.5 * Random.random()), 0.3, new Diffuse(new Color(0.8, 0.7, 0.9)));
    
    ArrayList<Shape> scene = new ArrayList<Shape>();
    scene.add(backgroundS3);
    scene.add(ground);
    scene.add(sphere1);
    scene.add(sphere2);
    scene.add(sphere3);
    scene.add(sphere4);
    scene.add(sphere5);
    scene.add(sphere6);
    scene.add(sphere7);
    Group group = new Group(scene);
    Raytracer pic = new Raytracer(camera, group);

    // Creates an image and iterates over all pixel positions inside the image with a sample rate of 12 (supersampling).
    Image imageS3 = new Image(width, height);
    imageS3.sample(pic, 12);

    // Write the image to disk.
    final String filename3S = "doc/a05-diffuse-spheres.png";
    imageS3.write(filename3S);
    System.out.println("Wrote image: " + filename3S);
  }
}
