package com.ThePinkAlliance.core.rev;

import com.revrobotics.CANSparkMax;

public class SparkMax extends CANSparkMax {

  public SparkMax(final int id, MotorType type) {
    super(id, type);
  }

  public void disableCloseLoopControl() {
    this.getPIDController().setReference(0, CANSparkMax.ControlType.kCurrent);
  }
}
