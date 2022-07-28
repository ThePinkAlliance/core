package com.ThePinkAlliance.core.joystick;

import com.ThePinkAlliance.core.util.joystick.JoystickMap;

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