package cgg.a10;

import java.util.ArrayList;

import cgg.Material.ConstColor;
import cgg.Material.Diffuse;
import cgg.Material.Glass;
import cgg.Material.Mirror;
import cgg.Scene.Image;
import cgg.Scene.Camera;
import cgg.Scene.Raytracer;
import cgg.Scene.Transformation;
import cgg.Shapes.*;
import cgg.Textures.Checkboard;
import cgg.Textures.PolkaDots;
import cgg.Textures.Texture;
import cgg.Textures.TextureTransform;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;

public class Main {

  public static void main(String[] args) {
    
    long startTime = System.currentTimeMillis();
    System.out.println("Start: " + startTime);

    final int width = 1000;
    final int height = 700;

    // This class instance defines the contents of the image.
    Camera camera1 = new Camera(Math.PI / 3, width, height, 0, new Direction(0, -8, 30), new Direction(1, 0, 0));
    Camera camera2 = new Camera(Math.PI / 3, width, height,  20, new Direction(0, -15, 25), new Direction(1, 0, 0));
    
    //textrues
    ConstColor backgorundC = new ConstColor(new TextureTransform(new Texture("img/360Room.png"), Matrix.scaling(1, 1, 1)));
    Glass sky = new Glass(new TextureTransform(new Texture("img/sky.png"), Matrix.scaling(1, 1, 1)), 1.0, 1.5);
    Diffuse checkers = new Diffuse(new TextureTransform((new Checkboard(50, new Color(0, 0, 0), new Color(1, 1, 1))), Matrix.scaling(1, 1, 1)));
    Mirror polkaDots = new Mirror(new TextureTransform((new PolkaDots(0.09, new Color(0, 0, 0), new Color(1, 1, 1), 0.02)), Matrix.scaling(1, 1, 1)));

    // setting
    Shape background = new Background(backgorundC);
    Shape kugel = new Sphere(new Point(0, -6, 10), 2, sky);
    Shape kugel2 = new Sphere(new Point(6, -10, 10), 2, polkaDots);
    Shape cylinder = new Cylinder(new Point(-6, -12, 10), new Direction(0, 1, 0), checkers, 2, 4, 50, 50);

    ArrayList<Shape> backgroundA = new ArrayList<Shape>();
    backgroundA.add(background);
    Transformation moveBack = new Transformation(Matrix.rotation(new Direction(0, 1, 0), 180));
    Group groupBackground = new Group(backgroundA, moveBack);

    ArrayList<Shape> setting = new ArrayList<Shape>();
    setting.add(kugel);
    setting.add(kugel2);
    setting.add(cylinder);
    setting.add(groupBackground);

    Group groupFinal = new Group(setting);

    Raytracer pic1 = new Raytracer(camera1, groupFinal);
    Raytracer pic2 = new Raytracer(camera2, groupFinal);

    // Creates an image and iterates over all pixel positions inside the image with a sample rate of 13 (supersampling).
    Image image1 = new Image(width, height);
    Image image2 = new Image(width, height);
    // try-catch to check exception with fast sampler
    try {
      int n = 13;
      image1.fasterSampler(pic1, n);
      image2.fasterSampler(pic2, n);
      System.out.print(n);
    } catch (Exception e) {
    }

    // Write the image to disk.
    final String filename1 = "doc/a10-1.png";
    image1.write(filename1);
    System.out.println("Wrote image: " + filename1);
    final String filename2 = "doc/a10-2.png";
    image2.write(filename2);
    System.out.println("Wrote image: " + filename2);

    long endTime = System.currentTimeMillis();
    System.out.println("End: " + endTime);
    long runtime = endTime - startTime;
    System.out.println("Time to run program: " + runtime);
    System.out.println("DONE!");
  }
}
