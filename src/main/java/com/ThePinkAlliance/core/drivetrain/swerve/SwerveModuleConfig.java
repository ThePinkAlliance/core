package com.ThePinkAlliance.core.drivetrain.swerve;

public class SwerveModuleConfig {

  public int motorSteerPort;
  public int motorPowerPort;
  public int canIDPort;

  public double steerOffset = 0;

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
}
