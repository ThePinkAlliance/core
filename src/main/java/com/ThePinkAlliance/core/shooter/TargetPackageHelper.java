package com.ThePinkAlliance.core.shooter;

public class TargetPackageHelper {

  public TargetPackage create(
    double ff,
    double kP,
    double rpm,
    double hoodPosition
  ) {
    return new TargetPackage() {
      @Override
      public double getKFF() {
        return ff;
      }

      @Override
      public double getKP() {
        return kP;
      }

      @Override
      public double getRpm() {
        return rpm;
      }

      @Override
      public double getHoodPosition() {
        return hoodPosition;
      }
    };
  }
}
