package com.ThePinkAlliance.core.drivetrain.swerve;

public class SwerveModuleConfig {

  private int motorSteerPort;
  private int motorPowerPort;
  private int canIDPort;

  private double driveRampRate = 0;
  private double steerOffset = 0;

  public SwerveModuleConfig(
    int motorSteerPort,
    int motorPowerPort,
    int canIDPort
  ) {
    this.canIDPort = canIDPort;
    this.motorPowerPort = motorPowerPort;
    this.motorSteerPort = motorSteerPort;
  }

  public SwerveModuleConfig(
    int motorSteerPort,
    int motorPowerPort,
    int canIDPort,
    double steerOffset
  ) {
    this.canIDPort = canIDPort;
    this.motorPowerPort = motorPowerPort;
    this.motorSteerPort = motorSteerPort;
    this.steerOffset = Math.toRadians(steerOffset);
  }

  public SwerveModuleConfig(
    int motorSteerPort,
    int motorPowerPort,
    int canIDPort,
    double steerOffset,
    double driveRampRate
  ) {
    this.canIDPort = canIDPort;
    this.motorPowerPort = motorPowerPort;
    this.motorSteerPort = motorSteerPort;
    this.driveRampRate = driveRampRate;
    this.steerOffset = Math.toRadians(steerOffset);
  }

  public int getCanCoderPort() {
    return this.canIDPort;
  }

  public int getMotorDrivePort() {
    return this.motorPowerPort;
  }

  public int getMotorSteerPort() {
    return this.motorSteerPort;
  }

  public double getSteerOffset() {
    return this.steerOffset;
  }

  public double getDriveRampRate() {
    return this.driveRampRate;
  }
}
