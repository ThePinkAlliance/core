package com.ThePinkAlliance.core.limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

  enum LED_MODE {
    BLINK(2),
    ON(3),
    OFF(1),
    PIPELINE(0);

    private int mode;

    LED_MODE(int mode) {
      this.mode = mode;
    }

    public int get() {
      return mode;
    }
  }

  enum GAME_TARGET_HEIGHTS {
    RAPID_REACT_TOP_HUB(102.375);

    private double height;

    GAME_TARGET_HEIGHTS(double height) {
      this.height = height;
    }

    public double get() {
      return height;
    }
  }

  LED_MODE CURRENT_LED_MODE;

  double HEIGHT_FROM_FLOOR;
  double MOUNTED_ANGLE;
  double REFLECTED_TAPE_HEIGHT;
  double HORIZONTAL_OFFSET;

  NetworkTable table;
  NetworkTableEntry ty;
  NetworkTableEntry tx;
  NetworkTableEntry tv;
  NetworkTableEntry ledMode;

  /**
   *
   * @param height_from_floor The limelight lens height from the floor in inches.
   * @param mounted_angle The current pitch angle of the limelight on its mount.
   */
  public Limelight(double height_from_floor, double mounted_angle) {
    this.HEIGHT_FROM_FLOOR = height_from_floor;
    this.MOUNTED_ANGLE = mounted_angle;
    this.REFLECTED_TAPE_HEIGHT = GAME_TARGET_HEIGHTS.RAPID_REACT_TOP_HUB.get();
    this.CURRENT_LED_MODE = LED_MODE.OFF;
    this.HORIZONTAL_OFFSET = 0;
    this.table = NetworkTableInstance.getDefault().getTable("limelight");
    this.ty = this.table.getEntry("ty");
    this.tx = this.table.getEntry("tx");
    this.ledMode = this.table.getEntry("ledMode");
    this.tv = this.table.getEntry("tv");

    // Configure the limelight led's, camera mode and pipeline
    this.ledMode.setNumber(this.CURRENT_LED_MODE.get());
    this.table.getEntry("camMode").setNumber(0);
    this.table.getEntry("pipeline").setNumber(0);
  }

  /**
   *
   * @param height_from_floor The limelight lens height from the floor in inches.
   * @param mounted_angle The current pitch angle of the limelight on its mount.
   * @param horizontal_offset The angluar offset of the limelight from the center of the robot.
   */
  public Limelight(
    double height_from_floor,
    double mounted_angle,
    double horizontal_offset
  ) {
    this.HEIGHT_FROM_FLOOR = height_from_floor;
    this.MOUNTED_ANGLE = mounted_angle;
    this.REFLECTED_TAPE_HEIGHT = GAME_TARGET_HEIGHTS.RAPID_REACT_TOP_HUB.get();
    this.CURRENT_LED_MODE = LED_MODE.OFF;
    this.HORIZONTAL_OFFSET = horizontal_offset;
    this.table = NetworkTableInstance.getDefault().getTable("limelight");
    this.ty = this.table.getEntry("ty");
    this.tx = this.table.getEntry("tx");
  }

  /**
   *
   * @param targetHeight The height of the reflective tape in inches.
   */
  public void configureTargetHeight(double targetHeight) {
    this.REFLECTED_TAPE_HEIGHT = targetHeight;
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

  public double calculateDistance() {
    double verticalOffset = ty.getDouble(0);
    double targetAngleDeg = MOUNTED_ANGLE + verticalOffset;
    double dist =
      (
        (REFLECTED_TAPE_HEIGHT - HEIGHT_FROM_FLOOR) /
        Math.tan(Math.toRadians(targetAngleDeg))
      );
    double error = dist;

    double errAccDistance = (dist / error);

    return errAccDistance;
  }
}
