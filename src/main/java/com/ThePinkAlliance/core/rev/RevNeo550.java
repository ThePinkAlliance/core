package com.ThePinkAlliance.core.rev;

import com.revrobotics.CANSparkMaxLowLevel;

public class RevNeo550 extends SparkMax {

  /**
   * This will configure a rev neo 550 with a safe amperage limit by default.
   * @param channel
   */
  public RevNeo550(final int channel) {
    super(channel, CANSparkMaxLowLevel.MotorType.kBrushless);
    this.setSmartCurrentLimit(20);
  }
}
