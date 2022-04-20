package com.ThePinkAlliance.core.rev;

import com.revrobotics.CANSparkMaxLowLevel;

public class RevNeo extends SparkMax {

  /**
   * This will configure a rev neo with a safe amperage limit by default.
   * @param channel
   */
  public RevNeo(final int channel) {
    super(channel, CANSparkMaxLowLevel.MotorType.kBrushless);
    this.setSmartCurrentLimit(50);
  }
}
