package com.ThePinkAlliance.core.joystick;

import com.ThePinkAlliance.core.joystick.Joystick.Axis;
import com.ThePinkAlliance.core.joystick.Joystick.Buttons;
import java.util.function.Supplier;

public class JoystickAxis {

  private Joystick joystick;
  private Axis axis;

  public JoystickAxis(Joystick joystick, Axis axis) {
    this.joystick = joystick;
    this.axis = axis;
  }

  public Supplier<Double> getInvertedSupplier() {
    return () -> (this.joystick.getJoystick().getRawAxis(this.axis.id) * -1);
  }
  
  public double getInverted() {
    return this.joystick.getJoystick().getRawAxis(this.axis.id) * -1;
  }

  public Supplier<Double> getSuppliedValue() { 
    return () -> this.joystick.getJoystick().getRawAxis(this.axis.id);
  }

  public double get() {
    return this.joystick.getJoystick().getRawAxis(this.axis.id);
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
