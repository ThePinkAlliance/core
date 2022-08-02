package com.ThePinkAlliance.core.limelight;

public enum GameTargetHeights {
  RAPID_REACT_TOP_HUB(102.375);

  private double height;

  GameTargetHeights(double height) {
    this.height = height;
  }

  public double get() {
    return height;
  }
}
