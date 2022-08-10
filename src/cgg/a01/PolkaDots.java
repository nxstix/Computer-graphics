package cgg.a01;

import cgtools.*;

public class PolkaDots implements Sampler {

  // Returns the color for the given position.
  public Color getColor(double x, double y) {
    double radius = 13;
    double grid = 30;
    double pointX = x % grid;
    double pointY = y % grid;
    double dx = pointX - grid / 2;
    double dy = pointY - grid / 2;
    double d = Math.sqrt(dx * dx + dy * dy);
    if(radius > d){
      // inside of the circles
      return cgtools.Vector.hsvToRgb(new Color(0, 0, x/480));
    } 
    // outside of the circles
    return new Color(0, 0, 0);
  }
}
