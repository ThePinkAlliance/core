package com.ThePinkAlliance.core.ctre.talon.motion;

import com.ctre.phoenix.motion.TrajectoryPoint;
import java.util.List;

public class MotionProfile {

  public enum PROFILE_TYPES {
    S_CURVE,
    TRAPEZOIDAL,
  }

  double maxRotations;
  double maxVelocity;
  double delay = 10;

  public MotionProfile(double maxRotations, double maxVelocity) {
    this.maxRotations = maxRotations;
    this.maxVelocity = maxVelocity;
  }

  public MotionProfile(double maxRotations, double maxVelocity, double delay) {
    this.maxRotations = maxRotations;
    this.maxVelocity = maxVelocity;
    this.delay = delay;
  }
}
