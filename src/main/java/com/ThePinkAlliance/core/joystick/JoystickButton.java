package com.ThePinkAlliance.core.joystick;

/**
 * This wrapper converts pink joystick handlers into ones interpretable by WPILib.
 */
public class JoystickButton
  extends edu.wpi.first.wpilibj2.command.button.JoystickButton {

  public JoystickButton(Joystick joystick, Joystick.Buttons button) {
    super(joystick.getJoystick(), button.id);
  }

  /**
   * Returns boolean if the operator is currently pressing the button.
   */
  public boolean isPressed() {
    return this.get();
  }

  /**
   * Returns boolean if the operator has released the button or not.
   */
  public boolean isReleased() {
    return !this.get();
  }
}
