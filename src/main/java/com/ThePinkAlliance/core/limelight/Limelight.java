package com.ThePinkAlliance.core.limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

  LedMode CURRENT_LED_MODE = LedMode.OFF;

  int CURRENT_CAM_MODE;
  int CURRENT_PIPELINE;

  double HEIGHT_FROM_FLOOR;
  double MOUNTED_ANGLE;
  double REFLECTED_TAPE_HEIGHT;
  double HORIZONTAL_OFFSET;

  NetworkTable table;
  NetworkTableEntry ty;
  NetworkTableEntry tx;
  NetworkTableEntry tv;
  NetworkTableEntry ledMode;
  NetworkTableEntry camMode;
  NetworkTableEntry pipeline;

  /**
   * @param height_from_floor The limelight lens height from the floor in inches.
   * @param mounted_angle     The current pitch angle of the limelight on its
   *                          mount.
   */
  public Limelight(double height_from_floor, double mounted_angle) {
    this(
        new LimelightConstants(height_from_floor, mounted_angle, GameTargetHeights.RAPID_REACT_TOP_HUB.get(),
            0));
  }

  /**
   * @param height_from_floor The limelight lens height from the floor in inches.
   * @param mounted_angle     The current pitch angle of the limelight on its
   *                          mount.
   * @param target            The game specific reflective target.
   */
  public Limelight(double height_from_floor, double mounted_angle, GameTargetHeights target) {
    this(
        new LimelightConstants(height_from_floor, mounted_angle, target.get(),
            0));
  }

  /**
   * @param height_from_floor The limelight lens height from the floor in inches.
   * @param mounted_angle     The current pitch angle of the limelight on its
   *                          mount.
   * @param horizontal_offset The angluar offset of the limelight from the center
   *                          of the robot.
   */
  public Limelight(
      double height_from_floor,
      double mounted_angle,
      double horizontal_offset) {
    this(
        new LimelightConstants(height_from_floor, mounted_angle, GameTargetHeights.RAPID_REACT_TOP_HUB.get(),
            horizontal_offset));
  }

  /**
   * @param height_from_floor The limelight lens height from the floor in inches.
   * @param mounted_angle     The current pitch angle of the limelight on its
   *                          mount.
   * @param horizontal_offset The angluar offset of the limelight from the center
   *                          of the robot.
   * @param target            The game specific reflective target.
   */
  public Limelight(
      double height_from_floor,
      double mounted_angle,
      double horizontal_offset, GameTargetHeights target) {
    this(
        new LimelightConstants(height_from_floor, mounted_angle, target.get(),
            horizontal_offset));
  }

  /**
   * @param constants Constants for the limelight it contains all the limelight
   *                  specific information like target height, limelight angle,
   *                  and the limelights height on the robot.
   */
  public Limelight(LimelightConstants constants) {
    this.HEIGHT_FROM_FLOOR = constants.getHeightFromFloor();
    this.MOUNTED_ANGLE = constants.getMountedAngle();
    this.REFLECTED_TAPE_HEIGHT = constants.getTargetHeight();
    this.HORIZONTAL_OFFSET = constants.getHorizontalOffset();

    configureLimelight();
  }

  private void configureLimelight() {
    this.table = NetworkTableInstance.getDefault().getTable("limelight");
    this.ty = this.table.getEntry("ty");
    this.tx = this.table.getEntry("tx");
    this.tv = this.table.getEntry("tv");

    this.ledMode = this.table.getEntry("ledMode");
    this.camMode = this.table.getEntry("camMode");
    this.pipeline = this.table.getEntry("pipeline");

    // Configure the limelight led's, camera mode and pipeline
    this.ledMode.setNumber(this.CURRENT_LED_MODE.get());
    this.camMode.setNumber(CURRENT_CAM_MODE);
    this.pipeline.setNumber(CURRENT_PIPELINE);
  }

  /**
   *
   * @param targetHeight The height of the reflective tape in inches.
   */
  public void configureTargetHeight(double targetHeight) {
    this.REFLECTED_TAPE_HEIGHT = targetHeight;
  }

  /**
   * This will reconfigure the limelight with the passed parameters.
   * 
   * @param constants LimelightConstants
   */
  public void reconfigureLimelight(LimelightConstants constants) {
    this.HEIGHT_FROM_FLOOR = constants.getHeightFromFloor();
    this.MOUNTED_ANGLE = constants.getMountedAngle();
    this.REFLECTED_TAPE_HEIGHT = constants.getTargetHeight();
    this.HORIZONTAL_OFFSET = constants.getHorizontalOffset();

    configureLimelight();
  }

  /**
   *
   * @param target The game specific reflective target.
   */
  public void configureTargetHeight(GameTargetHeights target) {
    this.configureTargetHeight(target.get());
  }

  public double calculateAngleOffset() {
    return tx.getDouble(0) + HORIZONTAL_OFFSET;
  }

  /**
   *
   * @return if any valid targets are found.
   */
  public boolean foundTarget() {
    return tv.getDouble(0) > 0;
  }

  /**
   *
   * @return amount of valid targets are found.
   */
  public double foundTargets() {
    return tv.getDouble(0);
  }

  /**
   * Returns the horizontal difference from the center of the detected target.
   * 
   * @return The offset in degress, range of (27 <-> -27)
   */
  public double getHorizontalDiff() {
    return this.tx.getDouble(0);
  }

  /**
   * In order to get accurate target info you need to run the limelight's onboard
   * cross-hair calibration.
   * 
   * @return The estimated distance in inches.
   */
  public double calculateDistance() {
    double verticalOffset = ty.getDouble(0);
    double targetAngleDeg = MOUNTED_ANGLE + verticalOffset;

    return ((REFLECTED_TAPE_HEIGHT - HEIGHT_FROM_FLOOR) /
        Math.tan(Math.toRadians(targetAngleDeg)));
  }

  /**
   * 
   * Changes the current led mode on the limelight to the desired one.
   */
  public void setLedMode(LedMode mode) {
    this.CURRENT_LED_MODE = mode;

    this.ledMode.setNumber(mode.get());
  }

  /**
   * 
   * Changes the current camera mode on the limelight to the desired one.
   */
  public void setCamMode(int mode) {
    this.camMode.setNumber(mode);
  }

  /**
   * 
   * Changes the current pipeline on the limelight to the desired one.
   */
  public void setPipeline(int pipeline) {
    this.pipeline.setNumber(pipeline);
  }

  /**
   * 
   * gets the pipeline thats in use by the limelight.
   */
  public int getSelectedPipeline() {
    return this.pipeline.getNumber(this.CURRENT_PIPELINE).intValue();
  }

  /**
   * 
   * gets the led mode thats in use by the limelight.
   */
  public int getSelectedLedMode() {
    return this.ledMode.getNumber(this.CURRENT_LED_MODE.get()).intValue();
  }

  /**
   * 
   * gets the camera mode thats in use by the limelight.
   */
  public int getSelectedCamMode() {
    return this.camMode.getNumber(this.CURRENT_CAM_MODE).intValue();
  }
}
