package com.ThePinkAlliance.core.pathweaver;

import com.ThePinkAlliance.core.util.Gains;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.util.ErrorMessages;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PathFactory {

  private SwerveDriveKinematics m_kinematics;
  private Trajectory m_trajectory;
  private PIDController m_xController;
  private PIDController m_yController;
  private ProfiledPIDController m_thetaController;
  private Supplier<Pose2d> m_poseSupplier;

  private Gains m_xGains;
  private Gains m_yGains;
  private Gains m_thetaGains;

  public PathFactory(
      SwerveDriveKinematics m_kinematics,
      Supplier<Pose2d> m_poseSupplier,
      Trajectory m_trajectory,
      Gains m_xGains,
      Gains m_yGains,
      Gains m_thetaGains,
      double maxVelocityMetersPerSecond,
      double maxAccelerationMetersPerSecond) {
    this.m_kinematics = m_kinematics;
    this.m_poseSupplier = m_poseSupplier;
    this.m_trajectory = m_trajectory;

    this.m_xGains = m_xGains;
    this.m_yGains = m_yGains;
    this.m_thetaGains = m_thetaGains;

    this.m_xController = new PIDController(this.m_xGains.kP, this.m_xGains.kI, this.m_xGains.kD);
    this.m_yController = new PIDController(this.m_yGains.kP, this.m_yGains.kI, this.m_yGains.kD);
    this.m_thetaController = new ProfiledPIDController(
        this.m_thetaGains.kP,
        this.m_thetaGains.kI,
        this.m_thetaGains.kD,
        new TrapezoidProfile.Constraints(
            maxVelocityMetersPerSecond,
            // ? this might need to be squared
            maxAccelerationMetersPerSecond));
  }

  public PathFactory(
      SwerveDriveKinematics m_kinematics,
      Supplier<Pose2d> m_poseSupplier,
      Gains m_xGains,
      Gains m_yGains,
      Gains m_thetaGains,
      double maxVelocityMetersPerSecond,
      double maxAccelerationMetersPerSecond) {
    this.m_kinematics = m_kinematics;
    this.m_poseSupplier = m_poseSupplier;

    this.m_xGains = m_xGains;
    this.m_yGains = m_yGains;
    this.m_thetaGains = m_thetaGains;

    this.m_xController = new PIDController(this.m_xGains.kP, this.m_xGains.kI, this.m_xGains.kD);
    this.m_yController = new PIDController(this.m_yGains.kP, this.m_yGains.kI, this.m_yGains.kD);
    this.m_thetaController = new ProfiledPIDController(
        this.m_thetaGains.kP,
        this.m_thetaGains.kI,
        this.m_thetaGains.kD,
        new TrapezoidProfile.Constraints(
            maxVelocityMetersPerSecond,
            // ? this might need to be squared
            maxAccelerationMetersPerSecond));
  }

  public void setTrajectory(Trajectory trajectory) {
    this.m_trajectory = trajectory;
  }

  public SwerveControllerCommand buildController(
      Consumer<SwerveModuleState[]> outputModuleStates,
      Subsystem... requirements) {
    return new SwerveControllerCommand(
        ErrorMessages.requireNonNullParam(
            this.m_trajectory,
            "trajectory",
            "buildController"),
        m_poseSupplier,
        m_kinematics,
        m_xController,
        m_yController,
        m_thetaController,
        outputModuleStates,
        requirements);
  }

  public SwerveControllerCommand buildController(
      Trajectory trajectory,
      Consumer<SwerveModuleState[]> outputModuleStates,
      Subsystem... requirements) {
    return new SwerveControllerCommand(
        ErrorMessages.requireNonNullParam(
            trajectory,
            "trajectory",
            "buildController"),
        m_poseSupplier,
        m_kinematics,
        m_xController,
        m_yController,
        m_thetaController,
        outputModuleStates,
        requirements);
  }

  public SwerveControllerCommand buildControllerWithThetaLimit(
      double min,
      double max,
      Consumer<SwerveModuleState[]> outputModuleStates,
      Subsystem... requirements) {
    this.m_thetaController.setIntegratorRange(min, max);

    return buildController(outputModuleStates, requirements);
  }
}
