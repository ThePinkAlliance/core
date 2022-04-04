package com.ThePinkAlliance.shooter;

public class TargetPackageFactory implements TargetPackage {

  double kP;
  double kFF;
  double rpm;
  double hoodPosition;

  public TargetPackageFactory(
    double kP,
    double kFF,
    double rpm,
    double hoodPosition
  ) {
    this.kFF = kFF;
    this.kP = kP;
    this.hoodPosition = hoodPosition;
    this.rpm = rpm;
  }

  public TargetPackage build() {
    return this;
  }
}
