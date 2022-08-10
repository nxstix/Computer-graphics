package cgg.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cgtools.*;

public class Image {
  protected int width;
  protected int height;
  protected int x;
  protected int y;
  protected Color color;
  protected double[] pixel;

  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    pixel = new double[width * height *3]; 
  }

  public void setPixel(int x, int y, Color color) {
    int position = (y * width + x) * 3;
    pixel[position] = Math.pow(color.r(), 1/2.2);
    pixel[position + 1] = Math.pow(color.g(), 1/2.2);
    pixel[position + 2] = Math.pow(color.b(), 1/2.2);
  }

  public void write(String filename) {
    ImageWriter.write(filename, pixel, width, height);
  }

  public void sample(Sampler s) {
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        setPixel(x, y, s.getColor(x, y));
      }
    }
  }

  public void sample(Sampler s, int n) {
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        Color colorSamp = new Color(0, 0, 0);
        for(int ix = 0; ix < n; ix++){
          for(int iy = 0; iy < n; iy++){
            double randomX = Random.random();
            double randomY = Random.random();
            double xs = x + (ix + randomX) / n; 
            double ys = y + (iy + randomY) / n;
            colorSamp = Vector.add(colorSamp, s.getColor(xs, ys));
          }
        }
        setPixel(x, y, Vector.divide(colorSamp, n * n));
      }
    }
  }

  public void fasterSampler(Sampler s, int n) throws InterruptedException, ExecutionException{
    int cores = Runtime.getRuntime().availableProcessors();
    // System.out.println(cores);
    ExecutorService pool = Executors.newFixedThreadPool(cores);
    List <Future<Color>> pixels = new ArrayList<>();
    for(int x = 0; x != width; x++){
      for(int y = 0; y != height; y++){
        pixels.add(pool.submit(new OnePixel(s, n, x, y)));
      }
    }
    for(int x = 0; x != width; x++){
      for(int y = 0; y != height; y++){
        setPixel(x, y, pixels.get(x * height + y).get());
      }
    }
    pool.shutdown();
  }
}