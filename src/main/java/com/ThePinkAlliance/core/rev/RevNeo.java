package com.ThePinkAlliance.core.rev;

import com.revrobotics.CANSparkMaxLowLevel;

// TODO: make commanding the motor easier

public class RevNeo extends SparkMax {

  /**
   * This will configure a rev neo 550 with a safe amperage limit by default.
   * @param channel
   */
  public RevNeo(final int channel) {
    super(channel, CANSparkMaxLowLevel.MotorType.kBrushless);
    this.setSmartCurrentLimit(50);
  }
}
