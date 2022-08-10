package cgg.a06;

import java.util.ArrayList;

import cgg.Material.ConstColor;
import cgg.Material.Diffuse;
import cgg.Material.Glass;
import cgg.Material.Metal;
import cgg.Material.Mirror;
import cgg.Scene.Image;
import cgg.Scene.Camera;
import cgg.Scene.Raytracer;
import cgg.Shapes.*;
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
    Camera camera = new Camera(Math.PI / 3, width, height, 0, new Direction(0, 0, 0), new Direction(0, 0, 0));
    Shape background = new Background(new ConstColor(new Color(1, 1, 1)));
    Shape ground = new Plane(new Point(0.0, -1, 0.0), new Direction(0, 1, 0), new Diffuse(new Color(0.95, 0.95, 0.95)), 15);

    // spheres infront of camera
    Shape sphere1 = new Sphere(new Point(0, Random.random() - 0.5, - 6 * Random.random()), 1, new Diffuse(new Color(1, 0.7, 0.3)));
    Shape sphere2 = new Sphere(new Point(2, Random.random(), - 6 * Random.random()), 0.8, new Diffuse(new Color(1, 0.7, 0.2)));
    Shape sphere3 = new Sphere(new Point(- 2, Random.random() - 0.5, - 6 * Random.random()), 0.8, new Diffuse(new Color(1, 0.5, 0.6)));
    Shape sphere4 = new Sphere(new Point(- 1, Random.random(), - 7 * Random.random()), 0.5, new Diffuse(new Color(9, 0.5, 0.4)));
    Shape sphere5 = new Sphere(new Point(- 2, - 0.5, - 7), 0.7, new Diffuse(new Color(1, 0.9, 0.3)));
    Shape sphere6 = new Sphere(new Point(1.5, Random.random(), - 7 * Random.random()), 0.5, new Diffuse(new Color(1, 0.7, 0.9)));
    Shape sphere7 = new Sphere(new Point(1, - 0.7, - 3.5 * Random.random()), 0.3, new Diffuse(new Color(0.8, 0.7, 0.9)));

    // mirror spheres
    Shape mirriorSphere1 = new Sphere(new Point(- 0.8, - 0.5, - 3), 0.5, new Mirror(new Color(1, 1, 1)));
    Shape mirriorSphere2 = new Sphere(new Point(2, 0, - 8), 1, new Mirror(new Color(0.95, 0.95, 0.95)));

    // metal spheres
    Shape metalSphere = new Sphere(new Point(0.55, - 0.6, - 4), 0.4, new Metal(new Color(1, 1, 1), 0.2));

    // spheres behind camera
    Shape sphere1BC = new Sphere(new Point(5, Random.random() - 0.2,  2 * Random.random()), 2, new Diffuse(new Color(13, 0.5, 0.4)));
    Shape sphere2BC = new Sphere(new Point(0.2, Random.random(), 3 * Random.random()), 0.9, new Diffuse(new Color(1, 0.5, 0.6)));
    Shape sphere3BC = new Sphere(new Point(- 2.5, - 0.3,  0), 0.9, new Diffuse(new Color(0.8, 0.5, 0.9)));

    // scene 1
    ArrayList<Shape> scene1 = new ArrayList<Shape>();
    scene1.add(background);
    scene1.add(ground);
    scene1.add(sphere1);
    scene1.add(sphere2);
    scene1.add(sphere3);
    scene1.add(sphere4);
    scene1.add(sphere5);
    scene1.add(sphere6);
    scene1.add(sphere7);
    scene1.add(mirriorSphere1);
    scene1.add(mirriorSphere2);
    scene1.add(sphere1BC);
    scene1.add(sphere2BC);
    scene1.add(sphere3BC);
    scene1.add(metalSphere);
    Group group1 = new Group(scene1);
    Raytracer pic1 = new Raytracer(camera, group1);

    // spheres infrom of camera
    Random.seed(172);
    Shape sphere8 = new Sphere(new Point(1, Random.random() - 0.5, - 11 * Random.random()), 1, new Diffuse(new Color(1, 0.7, 0.3)));
    Shape sphere9 = new Sphere(new Point(3, Random.random(), - 11 * Random.random()), 0.8, new Diffuse(new Color(1, 0.7, 0.2)));
    Shape sphere10 = new Sphere(new Point(- 3, Random.random() - 0.6, - 35 * Random.random()), 0.6, new Diffuse(new Color(1, 0.5, 0.6)));
    Shape sphere11 = new Sphere(new Point(-0.4, Random.random(), - 12 * Random.random()), 0.5, new Diffuse(new Color(9, 0.5, 0.4)));
    Shape sphere12 = new Sphere(new Point(-2, - 0.5, - 12), 0.7, new Diffuse(new Color(1, 0.9, 0.3)));
    Shape sphere13 = new Sphere(new Point(2.5, Random.random(), - 12 * Random.random()), 0.5, new Diffuse(new Color(1, 0.7, 0.9)));
    Shape sphere14 = new Sphere(new Point(1, - 0.7, - 8.5 * Random.random()), 0.3, new Diffuse(new Color(0.8, 0.7, 0.9)));

    // metal spheres
    Shape metalSphere2 = new Sphere(new Point(- 3.9, 1.3, - 15), 2.3, new Metal(new Color(0.9, 0.9, 0.9), 0.1));

    // mirror sphere
    Shape mirriorSphere3 = new Sphere(new Point(0, - 0.55, - 9), 0.4, new Mirror(new Color(1, 1, 1)));

    // glass sphere
    // density: air = 1.0; water = 1.3; glass = 1.5
    Shape glassSphere = new Sphere(new Point(0, - 0.15, - 4), 0.8, new Glass(new Color(1, 1, 1), 1, 1.5));

    // scene 2
    ArrayList<Shape> scene2 = new ArrayList<Shape>();
    scene2.add(background);
    scene2.add(ground);
    scene2.add(sphere8);
    scene2.add(sphere9);
    scene2.add(sphere10);
    scene2.add(sphere11);
    scene2.add(sphere12);
    scene2.add(sphere13);
    scene2.add(sphere14);
    scene2.add(metalSphere2);
    scene2.add(mirriorSphere3);
    scene2.add(glassSphere);
    Group group2 = new Group(scene2);
    Raytracer pic2 = new Raytracer(camera, group2);

    // Creates an image and iterates over all pixel positions inside the image with a sample rate of 13 (supersampling).
    Image image1 = new Image(width, height);
    image1.sample(pic1, 13);
    Image image2 = new Image(width, height);
    image2.sample(pic2, 13);

    // Write the image to disk.
    final String filename1 = "doc/a06-mirrors-glass-1.png";
    image1.write(filename1);
    System.out.println("Wrote image: " + filename1);
    final String filename2 = "doc/a06-mirrors-glass-2.png";
    image2.write(filename2);
    System.out.println("Wrote image: " + filename2);
  }
}
