package com.ThePinkAlliance.core.limelight;

/**
 * This store's information about the limelight and it's target.
 */
public class LimelightConstants {
  private double HEIGHT_FROM_FLOOR;
  private double MOUNTED_ANGLE;
  private double REFLECTED_TAPE_HEIGHT;
  private double HORIZONTAL_OFFSET;

  /**
   * @param HEIGHT_FROM_FLOOR     The limelight lens height from the floor in
   *                              inches.
   * @param MOUNTED_ANGLE         The current pitch angle of the limelight on its
   *                              mount.
   * @param REFLECTED_TAPE_HEIGHT The height of the reflective tape we are
   *                              tracking.
   */
  public LimelightConstants(double HEIGHT_FROM_FLOOR, double MOUNTED_ANGLE, double REFLECTED_TAPE_HEIGHT) {
    this.HEIGHT_FROM_FLOOR = HEIGHT_FROM_FLOOR;
    this.MOUNTED_ANGLE = MOUNTED_ANGLE;
    this.REFLECTED_TAPE_HEIGHT = REFLECTED_TAPE_HEIGHT;
    this.HORIZONTAL_OFFSET = 0;
  }

  /**
   * @param HEIGHT_FROM_FLOOR     The limelight lens height from the floor in
   *                              inches.
   * @param MOUNTED_ANGLE         The current pitch angle of the limelight on its
   *                              mount.
   * @param REFLECTED_TAPE_HEIGHT The height of the reflective tape we are
   *                              tracking.
   * @param HORIZONTAL_OFFSET     The angluar offset of the limelight from the
   *                              center
   *                              of the robot.
   */
  public LimelightConstants(double HEIGHT_FROM_FLOOR, double MOUNTED_ANGLE, double REFLECTED_TAPE_HEIGHT,
      double HORIZONTAL_OFFSET) {
    this.HEIGHT_FROM_FLOOR = HEIGHT_FROM_FLOOR;
    this.MOUNTED_ANGLE = MOUNTED_ANGLE;
    this.HORIZONTAL_OFFSET = HORIZONTAL_OFFSET;
    this.REFLECTED_TAPE_HEIGHT = REFLECTED_TAPE_HEIGHT;
  }

  /**
   * @param HEIGHT_FROM_FLOOR The limelight lens height from the floor in
   *                          inches.
   * @param MOUNTED_ANGLE     The current pitch angle of the limelight on its
   *                          mount.
   * @param TARGET            The reflective tape target we are
   *                          tracking.
   */
  public LimelightConstants(double HEIGHT_FROM_FLOOR, double MOUNTED_ANGLE, GameTargetHeights TARGET) {
    this.HEIGHT_FROM_FLOOR = HEIGHT_FROM_FLOOR;
    this.MOUNTED_ANGLE = MOUNTED_ANGLE;
    this.HORIZONTAL_OFFSET = 0;
    this.REFLECTED_TAPE_HEIGHT = TARGET.get();
  }

  /**
   * @param HEIGHT_FROM_FLOOR The limelight lens height from the floor in
   *                          inches.
   * @param MOUNTED_ANGLE     The current pitch angle of the limelight on its
   *                          mount.
   * @param TARGET            The reflective tape target we are
   *                          tracking.
   * @param HORIZONTAL_OFFSET The angluar offset of the
   *                          limelight from the
   *                          center
   *                          of the robot.
   */
  public LimelightConstants(double HEIGHT_FROM_FLOOR, double MOUNTED_ANGLE,
      GameTargetHeights TARGET,
      double HORIZONTAL_OFFSET) {
    this.HEIGHT_FROM_FLOOR = HEIGHT_FROM_FLOOR;
    this.MOUNTED_ANGLE = MOUNTED_ANGLE;
    this.HORIZONTAL_OFFSET = HORIZONTAL_OFFSET;
    this.REFLECTED_TAPE_HEIGHT = TARGET.get();
  }

  public double getHeightFromFloor() {
    return this.HEIGHT_FROM_FLOOR;
  }

  public double getMountedAngle() {
    return this.MOUNTED_ANGLE;
  }

  public double getTargetHeight() {
    return this.REFLECTED_TAPE_HEIGHT;
  }

  public double getHorizontalOffset() {
    return this.HORIZONTAL_OFFSET;
  }
}
