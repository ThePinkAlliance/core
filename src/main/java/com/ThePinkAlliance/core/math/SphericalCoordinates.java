package com.ThePinkAlliance.core.math;

import edu.wpi.first.math.geometry.Translation3d;

public class SphericalCoordinates {
  private final double elevation;
  private final double azimuth;
  private final double radial_distance;

  public SphericalCoordinates(double distance, double azimuth, double elevation) {
    this.elevation = elevation;
    this.radial_distance = distance;
    this.azimuth = azimuth;
  }

  public SphericalCoordinates() {
    this.elevation = 0;
    this.radial_distance = 0;
    this.azimuth = 0;
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
    double x = coordinates.radial_distance * Math.sin(coordinates.elevation) * Math.cos(coordinates.azimuth);
    double y = coordinates.radial_distance * Math.sin(coordinates.elevation) * Math.sin(coordinates.azimuth);
    double z = coordinates.radial_distance * Math.cos(coordinates.elevation);

    return new Translation3d(x, y, z);
  }

  /**
   * Subtracts the current coordinate by the passed coordinate.
   */
  public SphericalCoordinates subtract(SphericalCoordinates coordinates) {
    double r = this.radial_distance - coordinates.radial_distance;
    double theta = this.azimuth - coordinates.azimuth;
    double phi = this.elevation - coordinates.elevation;

    return new SphericalCoordinates(r, theta, phi);
  }

  /**
   * Calculates the azimuth by subtracting the passed coordinate by the current
   * azimuth and returns the difference.
   */
  public double calculateAzimuth(SphericalCoordinates coordinates) {
    return coordinates.azimuth - this.azimuth;
  }

  /**
   * Convert spherical coordinates to cartesian space.
   * 
   */
  public Translation3d toCartesian() {
    return SphericalCoordinates.toCartesian(this);
  }

  public double getAzimuth() {
    return azimuth;
  }

  public double getElevation() {
    return elevation;
  }

  public double getRadial_distance() {
    return radial_distance;
  }
}
