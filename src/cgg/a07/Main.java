package cgg.a07;

import java.util.ArrayList;

import cgg.Material.ConstColor;
import cgg.Material.Diffuse;
import cgg.Material.Glass;
import cgg.Material.Mirror;
import cgg.Scene.Image;
import cgg.Scene.Camera;
import cgg.Scene.Raytracer;
import cgg.Shapes.*;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;

public class Main {

  public static void main(String[] args) {
    final int width = 1000;
    final int height = 700;

    // This class instance defines the contents of the image.
    Camera camera1 = new Camera(Math.PI / 3, width, height,  0, new Direction(0, 0, 0), new Direction(0, 0, 1));
    Camera camera2 = new Camera(Math.PI / 3, width, height,  - 45, new Direction(0, 10, 0), new Direction(1, 0, 0));
    
    // setting
    Shape background = new Background(new ConstColor(new Color(0, 0, 0)));
    Shape ground = new Plane(new Point(0.0, -2, -10), new Direction(0, 1, 0), new Diffuse(new Color(0.1, 0.1, 0.1)), 40);
    Shape background1 = new Sphere(new Point(-3, 1, -14), 2, new Mirror(new Color(0.2, 0.2, 0.2)));
    Shape background2 = new Sphere(new Point(4, 1, -16), 2, new Mirror(new Color(0.6, 0.6, 0.6)));
    Shape background3 = new Sphere(new Point(-7, 1, -10), 2, new Mirror(new Color(0.2, 0.2, 0.2)));
    Shape background4 = new Sphere(new Point(6, 1, -9), 2, new Mirror(new Color(0.4, 0.4, 0.4)));
    Shape background5 = new Sphere(new Point(-5, -1.75, -9), 0.25, new ConstColor(new Color(0.8, 0.8, 0.8)));
    Shape background6 = new Sphere(new Point(-3, -1.75, -15), 0.25, new ConstColor(new Color(0.8, 0.8, 0.8)));
    Shape background7 = new Sphere(new Point(4, -1.75, -14), 0.25, new ConstColor(new Color(0.8, 0.8, 0.8)));
    Shape background8 = new Sphere(new Point(2, -1.75, -6), 0.25, new ConstColor(new Color(0.8, 0.8, 0.8)));
    Shape background9 = new Sphere(new Point(1.2, -1.75, -19), 0.25, new ConstColor(new Color(0.8, 0.8, 0.8)));
    
    // ghost 1
    Shape body1 = new Sphere(new Point(0, -0.5, -13), 1, new ConstColor(new Color(1, 0, 1)));
    Shape legL1 = new Cylinder(new Point(-0.3, -2, -13), new Direction(0, 1, 0), new ConstColor(new Color(1, 0, 1)), 0.1, 1);
    Shape legR1 = new Cylinder(new Point(0.3, -2, -13), new Direction(0, 1, 0), new ConstColor(new Color(1, 0, 1)), 0.1, 1);
    Shape armL1 = new Cylinder(new Point(-0.9, -1.5, -13), new Direction(0, 1, 0), new ConstColor(new Color(1, 0, 1)), 0.1, 1);
    Shape armR1 = new Cylinder(new Point(0.9, -1.5, -13), new Direction(0, 1, 0), new ConstColor(new Color(1, 0, 1)), 0.1, 1);
    Shape eyeL1 = new Sphere(new Point(-0.35, -0.4, -12.1), 0.1, new ConstColor(new Color(1, 1, 1)));
    Shape eyeR1 = new Sphere(new Point(0.35, -0.4, -12.1), 0.1, new ConstColor(new Color(1, 1, 1)));
    Shape pupilL1 = new Sphere(new Point(-0.35, -0.4, -12), 0.05, new Diffuse(new Color(0, 0, 0)));
    Shape pupilR1 = new Sphere(new Point(0.35, -0.4, -12), 0.05, new Diffuse(new Color(0, 0, 0)));
    // ghost 2
    Shape body2 = new Sphere(new Point(-2, 0, -7), 1, new ConstColor(new Color(0.5, 0, 1)));
    Shape legL2 = new Cylinder(new Point(-2.3, -2, -7), new Direction(0, 1, 0), new ConstColor(new Color(0.5, 0, 11)), 0.1, 1.5);
    Shape legR2 = new Cylinder(new Point(-1.7, -2, -7), new Direction(0, 1, 0), new ConstColor(new Color(0.5, 0, 1)), 0.1, 1.5);
    Shape armL2 = new Cylinder(new Point(-2.6, -1.5, -7), new Direction(0, 1, 0), new ConstColor(new Color(0.5, 0, 1)), 0.1, 1.5);
    Shape armR2 = new Cylinder(new Point(-1.4, -1.5, -7), new Direction(0, 1, 0), new ConstColor(new Color(0.5, 0, 1)), 0.1, 1.5);
    Shape eyeL2 = new Sphere(new Point(-2.4, 0, -6.2), 0.2, new ConstColor(new Color(1, 1, 1)));
    Shape eyeR2 = new Sphere(new Point(-1.6, 0, -6.2), 0.2, new ConstColor(new Color(1, 1, 1)));
    Shape pupilL2 = new Sphere(new Point(-2.4, 0, -6), 0.05, new Diffuse(new Color(0, 0, 0)));
    Shape pupilR2 = new Sphere(new Point(-1.6, 0, -6), 0.05, new Diffuse(new Color(0, 0, 0)));
    // ghost 3
    Shape body3 = new Sphere(new Point(3, 0.2, -10), 0.7, new ConstColor(new Color(0.1, 0, 1)));
    Shape legL3 = new Cylinder(new Point(2.8, -2, -10), new Direction(0, 1, 0), new ConstColor(new Color(0.1, 0, 1)), 0.1, 1.7);
    Shape legR3 = new Cylinder(new Point(3.2, -2, -10), new Direction(0, 1, 0), new ConstColor(new Color(0.1, 0, 1)), 0.1, 1.7);
    Shape armL3 = new Cylinder(new Point(3.5, -1, -10), new Direction(0, 1, 0), new ConstColor(new Color(0.1, 0, 1)), 0.1, 1);
    Shape armR3 = new Cylinder(new Point(2.5, -1, -10), new Direction(0, 1, 0), new ConstColor(new Color(0.1, 0, 1)), 0.1, 1);
    Shape eyeL3 = new Sphere(new Point(3.1, 0.2, -9.4), 0.15, new ConstColor(new Color(1, 1, 1)));
    Shape eyeR3 = new Sphere(new Point(2.5, 0.2, -9.6), 0.15, new ConstColor(new Color(1, 1, 1)));
    Shape pupilL3 = new Sphere(new Point(3.1, 0.2, -9.25), 0.05, new Diffuse(new Color(0, 0, 0)));
    Shape pupilR3 = new Sphere(new Point(2.4, 0.2, -9.45), 0.05, new Diffuse(new Color(0, 0, 0)));
    // magical glass ball
    Shape glass = new Sphere(new Point(0, 1, -4), 0.5, new Glass(new Color(1, 1, 1), 1.0, 1.5));
    Shape inside1 = new Sphere(new Point(0, 1, -4), 0.1, new ConstColor(new Color(1, 1, 1)));
    Shape inside2 = new Sphere(new Point(0.1, 1.1, -4), 0.1, new ConstColor(new Color(0.4, 0.4, 0.4)));
    Shape inside3 = new Sphere(new Point(-0.05, 1.1, -3.95), 0.09, new ConstColor(new Color(0.2, 0.2, 0.2)));
    Shape inside4 = new Sphere(new Point(-0.05, 0.9, -4), 0.05, new ConstColor(new Color(0.9, 0.9, 0.9)));
    Shape inside5 = new Sphere(new Point(0.1, 0.95, -3.95), 0.08, new ConstColor(new Color(0.1, 0.1, 0.1)));

    // scene
    ArrayList<Shape> scene = new ArrayList<Shape>();
    // scene
    scene.add(background);
    scene.add(ground);
    scene.add(background1);
    scene.add(background2);
    scene.add(background3);
    scene.add(background4);
    scene.add(background5);
    scene.add(background6);
    scene.add(background7);
    scene.add(background8);
    scene.add(background9);
    // ghost 1
    scene.add(body1);
    scene.add(legL1);
    scene.add(legR1);
    scene.add(armL1);
    scene.add(armR1);
    scene.add(eyeL1);
    scene.add(eyeR1);
    scene.add(pupilL1);
    scene.add(pupilR1);
    // ghost 2
    scene.add(body2);
    scene.add(legL2);
    scene.add(legR2);
    scene.add(armL2);
    scene.add(armR2);
    scene.add(eyeL2);
    scene.add(eyeR2);
    scene.add(pupilL2);
    scene.add(pupilR2);
    // ghost 3
    scene.add(body3);
    scene.add(legL3);
    scene.add(legR3);
    scene.add(armL3);
    scene.add(armR3);
    scene.add(eyeL3);
    scene.add(eyeR3);
    scene.add(pupilL3);
    scene.add(pupilR3);
    // magical glass ball
    scene.add(glass);
    scene.add(inside1);
    scene.add(inside2);
    scene.add(inside3);
    scene.add(inside4);
    scene.add(inside5);
    Group group = new Group(scene);
    Raytracer pic1 = new Raytracer(camera1, group);
    Raytracer pic2 = new Raytracer(camera2, group);

    // Creates an image and iterates over all pixel positions inside the image with a sample rate of 13 (supersampling).
    Image image1 = new Image(width, height);
    image1.sample(pic1, 13);
    Image image2 = new Image(width, height);
    image2.sample(pic2, 13);

    // Write the image to disk.
    final String filename1 = "doc/a07-1.png";
    image1.write(filename1);
    System.out.println("Wrote image: " + filename1);
    final String filename2 = "doc/a07-2.png";
    image2.write(filename2);
    System.out.println("Wrote image: " + filename2);
  }
}
