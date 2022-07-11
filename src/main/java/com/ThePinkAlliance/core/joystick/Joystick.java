package com.ThePinkAlliance.core.joystick;

import com.ThePinkAlliance.core.util.joystick.JoystickMap;

public class Joystick {

  private edu.wpi.first.wpilibj.Joystick joy;

  public enum Buttons {
    A(JoystickMap.BUTTON_A),
    B(JoystickMap.BUTTON_B),
    X(JoystickMap.BUTTON_X),
    Y(JoystickMap.BUTTON_Y),
    BACK(JoystickMap.BUTTON_BACK),
    START(JoystickMap.BUTTON_START),
    RIGHT_BUMPER(JoystickMap.RIGHT_BUMPER),
    LEFT_BUMPER(JoystickMap.LEFT_BUMPER),
    RIGHT_TRIGGER(JoystickMap.RIGHT_TRIGGER),
    LEFT_TRIGGER(JoystickMap.LEFT_TRIGGER),
    RIGHT_AXIS(JoystickMap.RIGHT_Y_AXIS_BUTTON),
    LEFT_AXIS(JoystickMap.LEFT_Y_AXIS_BUTTON);

    public final int id;

    Buttons(int id) {
      this.id = id;
    }
  }

  public enum PovType {
    NORTH(0),
    EAST(90),
    WEST(270),
    SOUTH(180);

    int id = 0;

    PovType(int id) {
      this.id = id;
    }
  }

  public enum Axis {
    LEFT_X(JoystickMap.LEFT_X_AXIS),
    LEFT_Y(JoystickMap.LEFT_Y_AXIS),
    RIGHT_X(JoystickMap.RIGHT_X_AXIS),
    RIGHT_Y(JoystickMap.RIGHT_Y_AXIS);

    public final int id;

    Axis(int id) {
      this.id = id;
    }
  }

  public Joystick(int port) {
    this.joy = new edu.wpi.first.wpilibj.Joystick(port);
  }

  protected edu.wpi.first.wpilibj.Joystick getJoystick() {
    return this.joy;
  }

  public JoystickButton getButton(Buttons button) {
    return new JoystickButton(this, button);
  }

  public JoystickAxis getAxis(Axis axis) {
    return new JoystickAxis(this, axis);
  }

  public boolean povActivated(PovType type) {
    int pov = this.joy.getPOV();

    if (pov == type.id) {
      return true;
    } else {
      return false;
    }
  }
}
