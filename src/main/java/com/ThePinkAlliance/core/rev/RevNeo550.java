package com.ThePinkAlliance.core.rev;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

// TODO: make commanding the motor easier

public class RevNeo550 extends SparkMax {

  /**
   * This will configure a rev neo with amperage limit's by default.
   * @param channel
   */
  public RevNeo550(final int channel) {
    super(channel, CANSparkMaxLowLevel.MotorType.kBrushless);
    this.setSmartCurrentLimit(20);
  }
}
