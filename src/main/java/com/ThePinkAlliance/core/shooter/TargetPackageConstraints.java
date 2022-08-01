package com.ThePinkAlliance.core.shooter;

import com.ThePinkAlliance.core.util.Gains;

public class TargetPackageConstraints {
  private Gains gainsRange;

  private double rpmSlope;

  private double rpmIntercept;

  private double hoodSlope;

  private double hoodIntercept;

  private double hoodMin;

  private double hoodMax;

  private double feedforwardMagic;

  public TargetPackageConstraints(Gains gains, double rpmSlope, double rpmIntercept, double hoodSlope,
      double hoodIntercept, double hoodMin, double hoodMax, double feedforwardMagic) {
    this.gainsRange = gains;
    this.rpmSlope = rpmSlope;
    this.rpmIntercept = rpmIntercept;
    this.hoodSlope = hoodSlope;
    this.hoodIntercept = hoodIntercept;
    this.hoodMin = hoodMax;
    this.feedforwardMagic = feedforwardMagic;
  }

  public double getFeedforwardMagic() {
    return feedforwardMagic;
  }

  public void setFeedforwardMagic(double feedforwardMagic) {
    this.feedforwardMagic = feedforwardMagic;
  }

  public double getHoodMax() {
    return hoodMax;
  }

  public void setHoodMax(double hoodMax) {
    this.hoodMax = hoodMax;
  }

  public double getHoodMin() {
    return hoodMin;
  }

  public void setHoodMin(double hoodMin) {
    this.hoodMin = hoodMin;
  }

  public double getHoodIntercept() {
    return hoodIntercept;
  }

  public void setHoodIntercept(double hoodIntercept) {
    this.hoodIntercept = hoodIntercept;
  }

  public double getHoodSlope() {
    return hoodSlope;
  }

  public void setHoodSlope(double hoodSlope) {
    this.hoodSlope = hoodSlope;
  }

  public double getRpmIntercept() {
    return rpmIntercept;
  }

  public void setRpmIntercept(double rpmIntercept) {
    this.rpmIntercept = rpmIntercept;
  }

  public double getRpmSlope() {
    return rpmSlope;
  }

  public void setRpmSlope(double rpmSlope) {
    this.rpmSlope = rpmSlope;
  }

  public Gains getGainsRange() {
    return gainsRange;
  }

  public void setGainsRange(Gains gainsRange) {
    this.gainsRange = gainsRange;
  }
}
