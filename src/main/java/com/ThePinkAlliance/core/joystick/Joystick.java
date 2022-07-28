package com.ThePinkAlliance.core.joystick;

public class Joystick {

  private edu.wpi.first.wpilibj.Joystick joy;

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
    return this.joy.getPOV() == type.id;
  }
}
