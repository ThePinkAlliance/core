package com.ThePinkAlliance.core.joystick;

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
