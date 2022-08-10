package cgg.a04;

import java.util.ArrayList;

import cgg.Scene.Image;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;

public class Main {

  public static void main(String[] args) {
    final int width = 1000;
    final int height = 700;

    // This class instance defines the contents of the image.
    // 3-spheres
    Camera camera = new Camera(Math.PI / 3, width, height);
    Shape backgroundS3 = new Background(new Color(1, 0, 1));
    Shape groundS3 = new Plane(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0), new Color(0.5, 0.5, 0.5));
    Shape globe1 = new Sphere(new Point(-1.0, -0.25, -2.5), 0.7, new Color(0.01, 0.01, 0.01));
    Shape globe2 = new Sphere(new Point(0.0, -0.25, -2.5),0.5, new Color(0.4, 0, 0.9));
    Shape globe3 = new Sphere(new Point(1.0, -0.25, -2.5), 0.7, new Color(0.2, 0.2, 0.2));
    ArrayList<Shape> scene3S = new ArrayList<Shape>();
    scene3S.add(backgroundS3);
    scene3S.add(groundS3);
    scene3S.add(globe1);
    scene3S.add(globe2);
    scene3S.add(globe3);
    Group groupS3 = new Group(scene3S);
    Raytracer pic3S = new Raytracer(camera, groupS3);

    // scene
    Shape background = new Background(new Color(0.1, 0.5, 1));
    Shape groundS = new Plane(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0), new Color(0.2, 1, 0.1));

    Shape body = new Sphere(new Point(0.0, -0.2, -3.0), 0.4, new Color(1, 1, 1));
    Shape leftArm = new Sphere(new Point(-0.3, -0.1, -2.9), 0.2, new Color(0, 0, 0));
    Shape rightArm = new Sphere(new Point(0.3, -0.1, -2.9), 0.2, new Color(0, 0, 0));
    Shape leftLeg = new Sphere(new Point(-0.14, -0.4, -2.6), 0.16, new Color(0, 0, 0));
    Shape rightLeg = new Sphere(new Point(0.14, -0.4, -2.6), 0.16, new Color(0, 0, 0));
    Shape head = new Sphere(new Point(0.0, 0.2, -2.9), 0.3, new Color(1, 1, 1));
    Shape leftEar = new Sphere(new Point(-0.2, 0.4, -2.8), 0.14, new Color(0, 0, 0));
    Shape rightEar = new Sphere(new Point(0.2, 0.4, -2.8), 0.14, new Color(0, 0, 0));
    Shape leftEye = new Sphere(new Point(-0.09, 0.24, -2.5), 0.08, new Color(0, 0, 0));
    Shape rightEye = new Sphere(new Point(0.09, 0.24, -2.5), 0.08, new Color(0, 0, 0));
    Shape leftPupil = new Sphere(new Point(-0.073, 0.21, -2.1), 0.03, new Color(1, 1, 1));
    Shape rightPupil = new Sphere(new Point(0.073, 0.21, -2.1), 0.03, new Color(1, 1, 1));
    Shape nose = new Sphere(new Point(0.0, 0.13, -2.1), 0.025, new Color(0, 0, 0));

    ArrayList<Shape> sceneS = new ArrayList<Shape>();
    sceneS.add(background);
    sceneS.add(groundS);
    sceneS.add(body);
    sceneS.add(leftArm);
    sceneS.add(rightArm);
    sceneS.add(leftLeg);
    sceneS.add(rightLeg);
    sceneS.add(head);
    sceneS.add(leftEar);
    sceneS.add(rightEar);
    sceneS.add(leftEye);
    sceneS.add(rightEye);
    sceneS.add(leftPupil);
    sceneS.add(rightPupil);
    sceneS.add(nose);
    Group groupS = new Group(sceneS);
    Raytracer picS = new Raytracer(camera, groupS);

     // Creates an image and iterates over all pixel positions inside the image with a sample rate of 12 (supersampling).
    Image imageS3 = new Image(width, height);
    imageS3.sample(pic3S, 12);
    Image imageS = new Image(width, height);
    imageS.sample(picS, 12);

    // Write the image to disk.
    final String filename3S = "doc/a04-3-spheres.png";
    imageS3.write(filename3S);
    System.out.println("Wrote image: " + filename3S);
    final String filenameS = "doc/a04-scene.png";
    imageS.write(filenameS);
    System.out.println("Wrote image: " + filenameS);
  }
}
