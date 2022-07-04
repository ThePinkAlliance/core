// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.ThePinkAlliance.core.ctre.fx.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class TalonFX_HardStopHoming extends CommandBase {
  TalonFX controller;
  double speed = -0.4;

  /** Creates a new HardStopHoming. */
  public TalonFX_HardStopHoming(TalonFX controller) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.controller = controller;
  }

  /** Creates a new HardStopHoming. */
  public TalonFX_HardStopHoming(TalonFX controller, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.speed = speed;
    this.controller = controller;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    controller.set(ControlMode.PercentOutput, speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.controller.set(ControlMode.PercentOutput, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return controller.getStatorCurrent() > 40;
  }
}
