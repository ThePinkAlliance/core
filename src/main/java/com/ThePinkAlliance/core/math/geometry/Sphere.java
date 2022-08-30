package com.ThePinkAlliance.core.math.geometry;

public class Sphere {
  /**
   * For information on how to calculate area for different shapes
   * https://www.grc.nasa.gov/WWW/K-12/airplane/area.html
   * 
   * @param radius radius of the Sphere
   * @return the area of the Sphere
   */
  public static double calculateAreaSphere(double radius) {
    return 4 * Math.PI * Math.pow(radius, 2.0);
  }

}
