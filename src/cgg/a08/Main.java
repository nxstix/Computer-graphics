package cgg.a08;

import java.util.ArrayList;

import cgg.Material.ConstColor;
import cgg.Material.Material;
import cgg.Material.Metal;
import cgg.Scene.Image;
import cgg.Scene.Camera;
import cgg.Scene.Raytracer;
import cgg.Scene.Transformation;
import cgg.Shapes.*;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;

public class Main {

  public static void main(String[] args) {
    final int width = 1000;
    final int height = 700;

    // This class instance defines the contents of the image.
    Camera camera1 = new Camera(Math.PI / 3, width, height,  0, new Direction(0, 0, 0), new Direction(0, 0, 1));
    Camera camera2 = new Camera(Math.PI / 3, width, height,  - 45, new Direction(0, 11, -3), new Direction(1, 0, 0));
    
    // setting
    Shape background = new Background(new ConstColor(new Color(0, 0, 0)));
    Shape ground = new Plane(new Point(0.0, -2, -10), new Direction(0, 1, 0), new ConstColor(new Color(1, 1, 1)));
    Shape kugel = new Sphere(new Point(0, 4, -15), 2, new Metal(new Color(0.6, 0.6, 0.6), 0));

    ArrayList<Shape> setting = new ArrayList<Shape>();
    setting.add(background);
    setting.add(ground);
    setting.add(kugel);
    Group groupSetting = new Group(setting);

    // robot
    Material metal = new Metal(new Color(0.4, 0.4, 0.4), 0.1);
    Shape legL = new Cylinder(new Point(-0.35, -2, -10), new Direction(0, 1, 0), metal, 0.15, 1.5);
    Shape legR = new Cylinder(new Point(0.35, -2, -10), new Direction(0, 1, 0), metal, 0.15, 1.5);
    Shape footL = new Sphere(new Point(-0.35, -2, -9.9), 0.2, metal);
    Shape footR = new Sphere(new Point(0.35, -2, -9.9), 0.2, metal);
    Shape body = new Cylinder(new Point(0, -0.5, -10), new Direction(0, 1, 0), metal, 0.5, 1.5);
    Shape neck = new Cylinder(new Point(0, 1, -10), new Direction(0, 1, 0), metal, 0.1, 0.2);
    Shape head = new Sphere(new Point(0, 1.6, -10), 0.5, metal);
    Shape shoulderL = new Sphere(new Point(-0.62, 0.87, -10), 0.17, metal);
    Shape shoulderR = new Sphere(new Point(0.62, 0.87, -10), 0.17, metal);
    Shape armL = new Cylinder(new Point(-0.65, -0.5, -10), new Direction(0, 1, 0), metal, 0.1, 1.45);
    Shape armR = new Cylinder(new Point(0.65, -0.5, -10), new Direction(0, 1, 0), metal, 0.1, 1.45);
    Shape handL = new Sphere(new Point(-0.65, -0.45, -10), 0.12, metal);
    Shape handR = new Sphere(new Point(0.65, -0.45, -10), 0.12, metal);
    Shape finger1L = new Cylinder(new Point(-0.57, -0.7, -10), new Direction(0, 1, 0), metal, 0.015, 0.2);
    Shape finger1R = new Cylinder(new Point(0.57, -0.7, -10), new Direction(0, 1, 0), metal, 0.015, 0.2);
    Shape finger2L = new Cylinder(new Point(-0.62, -0.72, -10), new Direction(0, 1, 0), metal, 0.015, 0.2);
    Shape finger2R = new Cylinder(new Point(0.62, -0.72, -10), new Direction(0, 1, 0), metal, 0.015, 0.2);
    Shape finger3L = new Cylinder(new Point(-0.68, -0.72, -10), new Direction(0, 1, 0), metal, 0.015, 0.2);
    Shape finger3R = new Cylinder(new Point(0.68, -0.72, -10), new Direction(0, 1, 0), metal, 0.015, 0.2);
    Shape finger4L = new Cylinder(new Point(-0.72, -0.7, -10), new Direction(0, 1, 0), metal, 0.015, 0.2);
    Shape finger4R = new Cylinder(new Point(0.72, -0.7, -10), new Direction(0, 1, 0), metal, 0.015, 0.2);

    ArrayList<Shape> robot = new ArrayList<Shape>();
    robot.add(legL);
    robot.add(legR);
    robot.add(footL);
    robot.add(footR);
    robot.add(body);
    robot.add(neck);
    robot.add(head);
    robot.add(shoulderL);
    robot.add(shoulderR);
    robot.add(armL);
    robot.add(armR);
    robot.add(handL);
    robot.add(handR);
    robot.add(finger1L);
    robot.add(finger1R);
    robot.add(finger2L);
    robot.add(finger2R);
    robot.add(finger3L);
    robot.add(finger3R);
    robot.add(finger4L);
    robot.add(finger4R);

    ArrayList<Shape> robots = new ArrayList<Shape>();
    for(int i = 0; i < 7; i += 2){
      Double xxP1 = (double) i;
      Double zz1 = (double) - i;
      Double xxN1 = (double) - i;
      Transformation moveP1 = new Transformation(Matrix.translation(xxP1, 0, zz1));
      Transformation moveN1 = new Transformation(Matrix.translation(xxN1, 0, zz1));
      Group groupRobotP1 = new Group(robot, moveP1);
      Group groupRobotN1 = new Group(robot, moveN1);
      Double xxP2 = (double) 6 - i;
      Double zz2 = (double) - 6 - i;
      Double xxN2 = (double) - 6 + i;
      Transformation moveP2 = new Transformation(Matrix.translation(xxP2, 0, zz2));
      Transformation moveN2 = new Transformation(Matrix.translation(xxN2, 0, zz2));
      Group groupRobotP2 = new Group(robot, moveP2);
      Group groupRobotN2 = new Group(robot, moveN2);
      robots.add(groupRobotP1);
      robots.add(groupRobotN1);
      robots.add(groupRobotP2);
      robots.add(groupRobotN2);
    }
    Group groupRobots = new Group(robots);

    ArrayList<Shape> picture = new ArrayList<Shape>();
    picture.add(groupRobots);
    picture.add(groupSetting);

    Group groupFinal = new Group(picture);
    Raytracer pic1 = new Raytracer(camera1, groupFinal);
    Raytracer pic2 = new Raytracer(camera2, groupFinal);

    // Creates an image and iterates over all pixel positions inside the image with a sample rate of 9 (supersampling).
    Image image1 = new Image(width, height);
    image1.sample(pic1, 9);
    Image image2 = new Image(width, height);
    image2.sample(pic2, 9);

    // Write the image to disk.
    final String filename1 = "doc/a08-1.png";
    image1.write(filename1);
    System.out.println("Wrote image: " + filename1);
    final String filename2 = "doc/a08-2.png";
    image2.write(filename2);
    System.out.println("Wrote image: " + filename2);
  }
}
