package com.ThePinkAlliance.core.simulation.ctre;

import com.ThePinkAlliance.core.simulation.SimProfile;
import com.ctre.phoenix.motorcontrol.can.*;
import java.util.*;

/**
 * Manages physics simulation for CTRE products.
 * 
 * @see <a href=
 *      "https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/tree/master/Java%20Talon%20FX%20(Falcon%20500)/MotionProfile/src/main/java/frc/robot/sim">Github</a>
 */
public class CtrePhysicsSim {

  private static final CtrePhysicsSim sim = new CtrePhysicsSim();

  /**
   * Gets the robot simulator instance.
   */
  public static CtrePhysicsSim getInstance() {
    return sim;
  }

  /**
   * Adds a TalonSRX controller to the simulator.
   *
   * @param talon
   *                        The TalonSRX device
   * @param accelToFullTime
   *                        The time the motor takes to accelerate from 0 to full,
   *                        in seconds
   * @param fullVel
   *                        The maximum motor velocity, in ticks per 100ms
   */
  public void addTalonSRX(
      TalonSRX talon,
      final double accelToFullTime,
      final double fullVel) {
    addTalonSRX(talon, accelToFullTime, fullVel, false);
  }

  /**
   * Adds a TalonSRX controller to the simulator.
   *
   * @param talon
   *                        The TalonSRX device
   * @param accelToFullTime
   *                        The time the motor takes to accelerate from 0 to full,
   *                        in seconds
   * @param fullVel
   *                        The maximum motor velocity, in ticks per 100ms
   * @param sensorPhase
   *                        The phase of the TalonSRX sensors
   */
  public void addTalonSRX(
      TalonSRX talon,
      final double accelToFullTime,
      final double fullVel,
      final boolean sensorPhase) {
    if (talon != null) {
      TalonSRXSimProfile simTalon = new TalonSRXSimProfile(
          talon,
          accelToFullTime,
          fullVel,
          sensorPhase);
      _simProfiles.add(simTalon);
    }
  }

  /**
   * Adds a TalonFX controller to the simulator.
   *
   * @param falcon
   *                        The TalonFX device
   * @param accelToFullTime
   *                        The time the motor takes to accelerate from 0 to full,
   *                        in seconds
   * @param fullVel
   *                        The maximum motor velocity, in ticks per 100ms
   */
  public void addTalonFX(
      TalonFX falcon,
      final double accelToFullTime,
      final double fullVel) {
    addTalonFX(falcon, accelToFullTime, fullVel, false);
  }

  /**
   * Adds a TalonFX controller to the simulator.
   *
   * @param falcon
   *                        The TalonFX device
   * @param accelToFullTime
   *                        The time the motor takes to accelerate from 0 to full,
   *                        in seconds
   * @param fullVel
   *                        The maximum motor velocity, in ticks per 100ms
   * @param sensorPhase
   *                        The phase of the TalonFX sensors
   */
  public void addTalonFX(
      TalonFX falcon,
      final double accelToFullTime,
      final double fullVel,
      final boolean sensorPhase) {
    if (falcon != null) {
      TalonFXSimProfile simFalcon = new TalonFXSimProfile(
          falcon,
          accelToFullTime,
          fullVel,
          sensorPhase);
      _simProfiles.add(simFalcon);
    }
  }

  /**
   * Adds a VictorSPX controller to the simulator.
   *
   * @param victor
   *               The VictorSPX device
   */
  public void addVictorSPX(VictorSPX victor) {
    if (victor != null) {
      VictorSPXSimProfile simVictor = new VictorSPXSimProfile(victor);
      _simProfiles.add(simVictor);
    }
  }

  /**
   * Runs the simulator:
   * - enable the robot
   * - simulate sensors
   */
  public void run() {
    // Simulate devices
    for (SimProfile simProfile : _simProfiles) {
      simProfile.run();
    }
  }

  private final ArrayList<SimProfile> _simProfiles = new ArrayList<SimProfile>();

  /*
   * scales a random domain of [0, 2pi] to [min, max] while prioritizing the peaks
   */
  static double random(double min, double max) {
    return ((max - min) /
        2 *
        Math.sin(Math.IEEEremainder(Math.random(), 2 * 3.14159)) +
        (max + min) /
            2);
  }

  static double random(double max) {
    return random(0, max);
  }
}
