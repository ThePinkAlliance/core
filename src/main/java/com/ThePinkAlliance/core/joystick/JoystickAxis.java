package com.ThePinkAlliance.core.joystick;

import com.ThePinkAlliance.core.joystick.InputProcessor;
import com.ThePinkAlliance.core.joystick.Joystick.Axis;
import com.ThePinkAlliance.core.joystick.Joystick.Buttons;
import com.ThePinkAlliance.core.util.joystick.JoystickUtils;
import java.util.function.Supplier;

public class JoystickAxis {

  private Joystick joystick;
  private Axis axis;

  private InputProcessor inputProcessor;

  private double axis_limit;

  private boolean cube = false;
  private boolean deadband = false;

  private double deadbandSetpoint = 0.05;

  public JoystickAxis(Joystick joystick, Axis axis) {
    this.joystick = joystick;
    this.axis = axis;
    this.axis_limit = 1;
  }

  public JoystickAxis clearDeadband() {
    this.deadband = false;

    return this;
  }

  public JoystickAxis clearCubing() {
    this.cube = false;

    return this;
  }

  public JoystickAxis withCustomProcessor(InputProcessor processor) {
    this.inputProcessor = processor;

    return this;
  }

  public JoystickAxis withLimit(double max) {
    this.axis_limit = max / 100;

    return this;
  }

  public Supplier<Double> invert() {
    double x =
      (this.joystick.getJoystick().getRawAxis(this.axis.id) * -1) * axis_limit;

    return () -> modAxis(x);
  }

  public JoystickAxis withAxisCubed() {
    this.cube = true;

    return this;
  }

  public JoystickAxis withDeadband() {
    this.deadband = true;

    return this;
  }

  public JoystickAxis withDeadband(double deadband) {
    this.deadband = true;
    this.deadbandSetpoint = deadband;

    return this;
  }

  public Supplier<Double> getSuppliedValue() {
    double x =
      this.joystick.getJoystick().getRawAxis(this.axis.id) * axis_limit;

    return () -> modAxis(x);
  }

  private double modAxis(double x) {
    if (inputProcessor == null) {
      if (cube) {
        x = Math.copySign(x * x * x, x);
      }

      if (deadband) {
        x = JoystickUtils.deadband(x, deadbandSetpoint);
      }
    } else {
      x = inputProcessor.handle(x);
    }

    return x;
  }

  public JoystickButton getJoystickButton() {
    return new JoystickButton(this.joystick, getAxisButton(this.axis));
  }

  private Buttons getAxisButton(Axis axis) {
    if (axis == Axis.LEFT_X || axis == Axis.LEFT_Y) {
      return Buttons.LEFT_AXIS;
    } else {
      return Buttons.RIGHT_AXIS;
    }
  }
}
