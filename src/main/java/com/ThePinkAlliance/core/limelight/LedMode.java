package com.ThePinkAlliance.core.limelight;

public enum LedMode {
  BLINK(2),
  ON(3),
  OFF(1),
  PIPELINE(0);

  private int mode;

  LedMode(int mode) {
    this.mode = mode;
  }

  public int get() {
    return mode;
  }
}
