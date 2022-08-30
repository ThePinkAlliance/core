package com.ThePinkAlliance.core.math;

/**
 * Resources from Glenn Research Center NASA
 *
 * https://courses.lumenlearning.com/boundless-physics/chapter/projectile-motion/
 *
 * https://www1.grc.nasa.gov/beginners-guide-to-aeronautics/flight-equations-with-drag/
 *
 * https://www1.grc.nasa.gov/beginners-guide-to-aeronautics/forces-on-a-baseball/
 *
 * https://www.desmos.com/calculator/on4xzwtdwz
 */
public class Projectile {

  /** this is the force of gravity on earth m/s */
  // private double g = Math.pow(9.8, 2);

  /**
   * Air Density kg/m3
   */
  private static double r = 1.225;

  /**
   * calculates the max distance using angle and velocity
   * 
   * @param angle    in degrees
   * @param velocity in m/s
   * @return the distance in meters
   */
  public static double calculateRange(double angle, double velocity) {
    return ((Math.pow(velocity, 2) * Math.pow(Math.sin(angle), 2)) / Math.pow(9.8, 2));
  }

  /**
   * calculating terminal velocity
   * https://www.grc.nasa.gov/www/k-12/airplane/termv.html
   * 
   * @param Cd       Drag coefficient
   * @param velocity the initial velocity of the sphere in m/s
   * @param A        is the cross section of the spheres area
   * @return
   */
  @Deprecated
  public static double calculateTerminalVelocity(
      double Cd,
      double velocity,
      double A) {
    double w = Cd * r * Math.pow(velocity, 2) * A / 2;

    return Math.sqrt((2 * w) / (Cd * r * A));
  }

  /**
   * https://sciencing.com/calculate-height-volume-7884648.html
   * https://www.grc.nasa.gov/WWW/K-12/airplane/drageq.html
   * 
   * @param r radius
   * @param c circumference
   * @return the area cross section of the sphere
   */
  public static double calculateCrossSection(double r, double c) {
    return Math.PI * Math.pow(r, 2);
  }
}
