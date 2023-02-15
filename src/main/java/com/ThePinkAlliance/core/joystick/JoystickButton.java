package com.ThePinkAlliance.core.joystick;

/**
 * This wrapper converts pink joystick handlers into ones interpretable by
 * WPILib.
 */
public class JoystickButton
    extends edu.wpi.first.wpilibj2.command.button.JoystickButton {

  public JoystickButton(Joystick joystick, Buttons button) {
    super(joystick.getJoystick(), button.id);
  }
}
