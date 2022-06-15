package com.ThePinkAlliance.core.rev;

import com.revrobotics.CANSparkMaxLowLevel;

public class RevNeo extends SparkMax {

  /**
   * This will configure a rev neo with a safe amperage limit by default.
   * @param id The CAN id of the REV Neo.
   */
  public RevNeo(final int id) {
    super(id, CANSparkMaxLowLevel.MotorType.kBrushless);
    this.setSmartCurrentLimit(40);
  }
}
