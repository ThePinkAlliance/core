package com.ThePinkAlliance.core.math;

import edu.wpi.first.math.geometry.Translation3d;

public class SphericalCoordinates {
  private final double phi;
  private final double theta;
  private final double r;

  public SphericalCoordinates(double r, double theta, double phi) {
    this.phi = phi;
    this.r = r;
    this.theta = theta;
  }

  public SphericalCoordinates() {
    this.phi = 0;
    this.r = 0;
    this.theta = 0;
  }

  /**
   * Convert from cartesian space to spherical space.
   * 
   * @param translation3d
   */
  public static SphericalCoordinates fromCartesian(Translation3d translation3d) {
    double x2 = translation3d.getX() * translation3d.getX();
    double y2 = translation3d.getY() * translation3d.getY();
    double z2 = translation3d.getZ() * translation3d.getZ();

    double projection = Math.sqrt(x2 + y2 + z2);
    double theta = Math.atan2(translation3d.getY(), translation3d.getX());
    double phi = Math.acos(translation3d.getZ() / (Math.sqrt(x2 + y2 + z2)));

    return new SphericalCoordinates(projection, theta, phi);
  }

  /**
   * Convert spherical coordinates to cartesian space.
   * 
   * @param coordinates
   */
  public static Translation3d toCartesian(SphericalCoordinates coordinates) {
    double x = coordinates.r * Math.sin(coordinates.phi) * Math.cos(coordinates.theta);
    double y = coordinates.r * Math.sin(coordinates.phi) * Math.sin(coordinates.theta);
    double z = coordinates.r * Math.cos(coordinates.phi);

    return new Translation3d(x, y, z);
  }

  public SphericalCoordinates subtract(SphericalCoordinates coordinates) {
    double r = this.r - coordinates.r;
    double theta = this.theta - coordinates.theta;
    double phi = this.phi - coordinates.phi;

    return new SphericalCoordinates(r, theta, phi);
  }

  /**
   * Convert spherical coordinates to cartesian space.
   * 
   */
  public Translation3d toCartesian() {
    return SphericalCoordinates.toCartesian(this);
  }

  public double getTheta() {
    return theta;
  }

  public double getPhi() {
    return phi;
  }

  public double getR() {
    return r;
  }

}
