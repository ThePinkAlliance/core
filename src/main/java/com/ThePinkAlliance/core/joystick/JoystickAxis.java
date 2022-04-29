package com.ThePinkAlliance.core.joystick;

import com.ThePinkAlliance.core.joystick.Joystick.Axis;
import com.ThePinkAlliance.core.joystick.Joystick.Buttons;
import java.util.function.Supplier;

public class JoystickAxis {

  private Joystick joystick;
  private Axis axis;
  private double axis_limit;

  public JoystickAxis(Joystick joystick, Axis axis) {
    this.joystick = joystick;
    this.axis = axis;
    this.axis_limit = 1;
  }

  public JoystickAxis limit(double max) {
    this.axis_limit = max / 100;

    return this;
  }

  public Supplier<Double> invert() {
    return () ->
      (
        (this.joystick.getJoystick().getRawAxis(this.axis.id) * -1) * axis_limit
      );
  }

  public Supplier<Double> getSuppliedValue() {
    return () ->
      (this.joystick.getJoystick().getRawAxis(this.axis.id) * axis_limit);
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
