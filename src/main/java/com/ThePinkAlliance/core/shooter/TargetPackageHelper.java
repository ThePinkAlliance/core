package com.ThePinkAlliance.core.shooter;

public class TargetPackageHelper {

  public TargetPackage calculatePackageFromDistance(TargetPackageConstraints constraints, double distance) {
    /**
     * SLF: linear formula based on testing with limelight and shooting from various
     * positions
     */
    double rpm = (constraints.getRpmSlope() * distance) + constraints.getRpmIntercept();

    /*
     * The y-intercept is negative because of the of the hood operating in negative
     * values.
     */
    double hoodPosition = (constraints.getHoodSlope() * distance) - constraints.getHoodIntercept();

    if (hoodPosition <= constraints.getHoodMax())
      hoodPosition = constraints.getHoodMax();
    else if (hoodPosition > constraints.getHoodMin())
      hoodPosition = constraints.getHoodMin();

    double Kf = rpm / constraints.getFeedforwardMagic();
    double Kp = constraints.getGainsRange().kP;

    return create(Kf, Kp, rpm, hoodPosition);
  }

  public TargetPackage create(
      double ff,
      double kP,
      double rpm,
      double hoodPosition) {
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
