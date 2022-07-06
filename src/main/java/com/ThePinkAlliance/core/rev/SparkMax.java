package com.ThePinkAlliance.core.rev;

import com.revrobotics.CANSparkMax;

public class SparkMax extends CANSparkMax {

  /**
   * Creates a new REV Spark Max with additional util methods.
   * 
   * @param id   The CAN id of the REV Spark Max.
   * @param type The type of motor on the REV Spark Max.
   */
  public SparkMax(final int id, MotorType type) {
    super(id, type);
  }

  /**
   * Disables integrated PID control on the REV Spark Max.
   */
  public void disableCloseLoopControl() {
    this.getPIDController().setReference(0, CANSparkMax.ControlType.kCurrent);
  }
}
