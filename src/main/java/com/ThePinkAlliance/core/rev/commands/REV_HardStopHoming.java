// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.ThePinkAlliance.core.rev.commands;

import com.ThePinkAlliance.core.rev.SparkMax;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class REV_HardStopHoming extends CommandBase {
  SparkMax controller;
  double speed = -0.4;

  MotorType type;

  public enum MotorType {
    NEO550(20),
    NEO(40);

    double value;

    MotorType(double r) {
      r = value;
    }

    public double get() {
      return value;
    }
  }

  /** Creates a new HardStopHoming. */
  public REV_HardStopHoming(SparkMax controller, MotorType type) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.type = type;
    this.controller = controller;
  }

  /** Creates a new HardStopHoming. */
  public REV_HardStopHoming(SparkMax controller, MotorType type, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.type = type;
    this.speed = speed;
    this.controller = controller;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    controller.set(speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.controller.disable();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return controller.getOutputCurrent() > type.get();
  }
}
