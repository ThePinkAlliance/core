package com.ThePinkAlliance.core.math;

import org.checkerframework.checker.units.qual.A;

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
  private double g = Math.pow(9.8, 2);

  /**
   * Air Density kg/m3
   */
  private double r = 1.225;

  /**
   * calculates the max distance using angle and velocity
   * @param angle in degrees
   * @param velocity in m/s
   * @return the distance in meters
   */
  public static double calculateRange(double angle, double velocity) {
    return (
      (Math.pow(velocity, 2) * Math.pow(Math.sin(angle), 2)) / Math.pow(9.8, 2)
    );
  }

  /**
   * caluculating terminal velocity
   * https://www.grc.nasa.gov/www/k-12/airplane/termv.html
   * @param Cd Drag coefficient
   * @param velocity
   * @param A is the cross section of the spheres area
   * @return
   */
  @Deprecated
  public double calculateTerminalVelocity(
    double Cd,
    double velocity,
    double A
  ) {
    double w = Cd * this.r * Math.pow(velocity, 2) * A / 2;

    return Math.sqrt((2 * w) / (Cd * r * A));
  }

  /**
   * https://sciencing.com/calculate-height-volume-7884648.html
   * https://www.grc.nasa.gov/WWW/K-12/airplane/drageq.html
   * @param r radius
   * @param c circumference
   * @return the area cross section of the sphere
   */
  public double calculateCrossSection(double r, double c) {
    return Math.PI * Math.pow(r, 2);
  }

  /**
   * For information on how to calculate area for different shapes
   * https://www.grc.nasa.gov/WWW/K-12/airplane/area.html
   * @param radius
   * @return the area of the circle
   */
  public double calculateAreaCircle(double radius) {
    return 4 * Math.PI * Math.pow(radius, 2.0);
  }
}
