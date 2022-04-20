package com.ThePinkAlliance.core.util;

public class JoystickUtils {

  public static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  /**
   * Apply deadband to the joystick input and Cube the output
   * @param value is the raw joystick input
   * @return the filtered joystick input
   */
  public static double modifyAxisCubed(double value) {
    // Deadband
    value = deadband(value, 0.05);

    // Cubing due to raw power until robot reaches competition weight.
    value = Math.copySign(value * value * value, value);

    return value;
  }

  /**
   * Apply deadband to the joystick input and Cube the output
   * @param value is the raw joystick input
   * @param deadband is the wanted deadband applied
   * @return the filtered joystick input
   */
  public static double modifyAxisCubed(double value, double deadband) {
    // Deadband
    value = deadband(value, deadband);

    // Cubing due to raw power until robot reaches competition weight.
    value = Math.copySign(value * value * value, value);

    return value;
  }
}
