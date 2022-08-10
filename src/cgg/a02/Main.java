package cgg.a02;

import cgg.Scene.Image;

public class Main {

  public static void main(String[] args) {
    final int width = 480;
    final int height = 270;

    // This class instance defines the contents of the image.
    ColoredDiscs circles = new ColoredDiscs(width, height, 33);

    // Creates an image and iterates over all pixel positions inside the image.
    Image image1 = new Image(width, height);
    image1.sample(circles);
    // Creates an image and iterates over all pixel positions inside the image with a sample rate of 10 (supersampling).
    Image image2 = new Image(width, height);
    image2.sample(circles, 10);

    // Write the image to disk.
    final String filename1 = "doc/a02-discs.png";
    final String filename2 = "doc/a02-discs-supersampling.png";
    image1.write(filename1);
    image2.write(filename2);
    System.out.println("Wrote image: " + filename1);
    System.out.println("Wrote image: " + filename2);
  }
}
