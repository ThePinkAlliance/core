package com.ThePinkAlliance.core.rev;

import com.revrobotics.CANSparkMaxLowLevel;

public class RevNeo550 extends SparkMax {

  /**
   * This will configure a REV Neo 550 with a safe amperage limit by default.
   * @param channel The CAN id of the REV Neo 500.
   */
  public RevNeo550(final int channel) {
    super(channel, CANSparkMaxLowLevel.MotorType.kBrushless);
    this.setSmartCurrentLimit(20);
  }
}
