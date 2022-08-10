/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a01;

import static cgtools.Vector.*;
import cgg.Scene.Image;

public class Main {

  public static void main(String[] args) {
    final int width = 480;
    final int height = 270;

    // This class instance defines the contents of the image.
    Circles contentK = new Circles(green);
    PolkaDots contentP = new PolkaDots();

    // Creates an image and iterates over all pixel positions inside the image.
    Image image1 = new Image(width, height);
    Image image2 = new Image(width, height);
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Sets the color for one particular pixel.
        image1.setPixel(x, y, contentK.getColor(x, y));
        image2.setPixel(x, y, contentP.getColor(x, y));
      }
    }

    // Write the image to disk.
    final String filename1 = "doc/a01-disc.png";
    image1.write(filename1);
    System.out.println("Wrote image: " + filename1);

    final String filename2 = "doc/a01-polka-dots.png";
    image2.write(filename2);
    System.out.println("Wrote image: " + filename2);
  }
}
