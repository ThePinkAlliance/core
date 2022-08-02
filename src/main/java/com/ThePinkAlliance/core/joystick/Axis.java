package com.ThePinkAlliance.core.joystick;

import com.ThePinkAlliance.core.util.joystick.JoystickMap;

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