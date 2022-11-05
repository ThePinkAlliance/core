// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.ThePinkAlliance.core.physics;

/** This stores the acceleration in the X,Y,Z directions. */
public class Acceleration3d {
  private double accelX;
  private double accelY;
  private double accelZ;

  public Acceleration3d(double accelX, double accelY, double accelZ) {
    this.accelX = accelX;
    this.accelY = accelY;
    this.accelZ = accelZ;
  }

  public double getAccelX() {
    return accelX;
  }

  public double getAccelZ() {
    return accelZ;
  }

  public double getAccelY() {
    return accelY;
  }
}
