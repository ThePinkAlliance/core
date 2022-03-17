package com.ThePinkAlliance.core.math;

/**
 * Glenn Research Center NASA
 * https://www1.grc.nasa.gov/beginners-guide-to-aeronautics/flight-equations-with-drag/
 */
public class Projectile {

  private double weight;
  private double angle;

  /** this is the force of gravity on earth m/s */
  private double g = 9.8;

  /**
   * This is the mass of the projectile
   */
  private double m;

  /**
   * Gas Density kg/m3
   */
  private double r = 1.225;

  private double c;

  private double radius = 1;

  private double area = calculateAreaCircle(radius);

  public Projectile(double diameter, double weight, double angle) {
    this.weight = weight;
    this.radius = diameter / 2;
    this.angle = angle;

    this.m = weight / g;
    this.c = 2 * Math.PI * r;
  }

  public double calulateVelocity(double distance) {
    return 0;
  }

  public double calulateVelocity(
    double distance,
    double velocity,
    double angle
  ) {
    // Drag coefficient
    double Cd = r * (velocity / 2) * area;
    double areaCrossSection = this.calculateCrossSection(r, c);
    double Vt = this.calculateTerminalVelocity(Cd, velocity, areaCrossSection);
    double drag = .5 * Cd * r * areaCrossSection * Math.pow(Vt, 2);

    this.angle = angle;

    return 0;
  }

  /**
   * caluculating terminal velocity
   * https://www.grc.nasa.gov/www/k-12/airplane/termv.html
   * @param Cd
   * @param velocity
   * @param A
   * @return
   */
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
