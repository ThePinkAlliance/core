// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.ThePinkAlliance.core.drivetrain.swerve;

import java.util.function.Supplier;

import com.ThePinkAlliance.core.physics.Acceleration3d;
import com.ThePinkAlliance.swervelib.Mk4SwerveModuleHelper;
import com.ThePinkAlliance.swervelib.Mk4iSwerveModuleHelper;
import com.ThePinkAlliance.swervelib.SwerveModule;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class SwerveBase extends SubsystemBase {
  public SwerveModule frontLeftModule;
  public SwerveModule frontRightModule;
  public SwerveModule backLeftModule;
  public SwerveModule backRightModule;

  protected AHRS gyro;

  protected ChassisSpeeds chassisSpeeds;
  protected SwerveDriveKinematics kinematics;
  protected SwerveModuleState[] states;

  protected ShuffleboardTab tab;

  /** Creates a new BaseFoundation. */
  public SwerveBase(double trackWidth, double wheelBase, String tabName) {
    this.chassisSpeeds = new ChassisSpeeds();
    this.tab = Shuffleboard.getTab(tabName);
    this.gyro = new AHRS();

    this.kinematics = new SwerveDriveKinematics(
        // Front Left
        new Translation2d(
            trackWidth / 2.0,
            wheelBase / 2.0),
        // Front Right
        new Translation2d(
            trackWidth / 2.0,
            -wheelBase / 2.0),
        // Back Left
        new Translation2d(
            -trackWidth / 2.0,
            wheelBase / 2.0),
        // Back Right
        new Translation2d(
            -trackWidth / 2.0,
            -wheelBase / 2.0));

    this.states = kinematics.toSwerveModuleStates(new ChassisSpeeds());
  }

  protected void configureMk4(Mk4SwerveModuleHelper.GearRatio ratio, SwerveModuleConfig frontLeftConfig,
      SwerveModuleConfig frontRightConfig, SwerveModuleConfig backRightConfig, SwerveModuleConfig backLeftConfig) {
    this.frontLeftModule = Mk4SwerveModuleHelper.createFalcon500(
        tab
            .getLayout("Front Left Module", BuiltInLayouts.kList)
            .withSize(2, 4)
            .withPosition(0, 0),
        ratio,
        frontLeftConfig);

    this.frontRightModule = Mk4SwerveModuleHelper.createFalcon500(
        tab
            .getLayout("Front Right Module", BuiltInLayouts.kList)
            .withSize(2, 4)
            .withPosition(2, 0),
        ratio,
        frontRightConfig);

    this.backRightModule = Mk4SwerveModuleHelper.createFalcon500(
        tab
            .getLayout("Back Right Module", BuiltInLayouts.kList)
            .withSize(2, 4)
            .withPosition(4, 0),
        ratio,
        backRightConfig);

    this.backLeftModule = Mk4SwerveModuleHelper.createFalcon500(
        tab
            .getLayout("Back Left Module", BuiltInLayouts.kList)
            .withSize(2, 4)
            .withPosition(6, 0),
        ratio,
        backLeftConfig);
  }

  protected void configureMk4i(Mk4iSwerveModuleHelper.GearRatio ratio, SwerveModuleConfig frontLeftConfig,
      SwerveModuleConfig frontRightConfig, SwerveModuleConfig backRightConfig, SwerveModuleConfig backLeftConfig) {
    this.frontLeftModule = Mk4iSwerveModuleHelper.createFalcon500(
        tab
            .getLayout("Front Left Module", BuiltInLayouts.kList)
            .withSize(2, 4)
            .withPosition(0, 0),
        ratio,
        frontLeftConfig);

    this.frontRightModule = Mk4iSwerveModuleHelper.createFalcon500(
        tab
            .getLayout("Front Right Module", BuiltInLayouts.kList)
            .withSize(2, 4)
            .withPosition(2, 0),
        ratio,
        frontRightConfig);

    this.backRightModule = Mk4iSwerveModuleHelper.createFalcon500(
        tab
            .getLayout("Back Right Module", BuiltInLayouts.kList)
            .withSize(2, 4)
            .withPosition(4, 0),
        ratio,
        backRightConfig);

    this.backLeftModule = Mk4iSwerveModuleHelper.createFalcon500(
        tab
            .getLayout("Back Left Module", BuiltInLayouts.kList)
            .withSize(2, 4)
            .withPosition(6, 0),
        ratio,
        backLeftConfig);
  }

  /**
   * Takes ChassisSpeed object and converts it to swerve module states to send to
   * all the modules.
   *
   * @param speeds
   */
  public void drive(ChassisSpeeds speeds) {
    chassisSpeeds = speeds;
  }

  /**
   * @return Kinematics of the robot
   */
  public SwerveDriveKinematics getKinematics() {
    return kinematics;
  }

  /**
   * This resets the gyroscope's Yaw axis to zero.
   */
  public void zeroGyro() {
    gyro.reset();
  }

  /**
   * This resets the odometry to the given position and sets the rotation to the
   * current one from the gyro.
   */
  public abstract void resetOdometry(Pose2d pose);

  /**
   * Returns the robot's current rotation.
   *
   * @return the robot's current rotation.
   */
  public Rotation2d getRotation() {
    if (gyro.isMagnetometerCalibrated()) {
      return Rotation2d.fromDegrees(gyro.getFusedHeading());
    }

    return Rotation2d.fromDegrees(360.0 - gyro.getYaw());
  }

  /**
   * Set's the current states for all the Swerve modules to the desired one's.
   *
   * @param states swerve pod states
   */
  public abstract void setStates(SwerveModuleState... states);

  /**
   * Returns the heading of the robot in degrees.
   */
  public abstract double getSensorYaw();

  /**
   * Returns the current odometry pose of the robot.
   */
  public abstract Pose2d getPose();

  /**
   * Returns the current odometry pose of the robot as a supplier.
   */
  public abstract Supplier<Pose2d> getPoseSupplier();

  /**
   * Reset the encoder counts on all the pod drive motors.
   */
  public void resetDriveMotors() {
    this.backLeftModule.resetDrive();
    this.backRightModule.resetDrive();
    this.frontLeftModule.resetDrive();
    this.frontRightModule.resetDrive();
  }

  /**
   * @return ChassisSpeeds of the robot
   */
  public ChassisSpeeds getChassisSpeeds() {
    return chassisSpeeds;
  }

  /**
   * Returns all the swerve module states
   */
  public SwerveModuleState[] getModuleStates() {
    return this.states;
  }

  /**
   * This will park all the swerve pods by setting their speed and headings to
   * zero.
   */
  public void parkAllPods() {
    for (int i = 0; i < this.states.length; i++) {
      SwerveModuleState state = states[i];

      state.angle = new Rotation2d();
      state.speedMetersPerSecond = 0;

      states[i] = state;
    }

    setStates(states);
  }

  /**
   * Returns accelation info from the gyro.
   */
  public Acceleration3d getAcceleration() {
    return new Acceleration3d(gyro.getWorldLinearAccelX(), gyro.getWorldLinearAccelY(), gyro.getWorldLinearAccelZ());
  }

  /**
   * This resets the odometry to the given position and sets the rotation to the
   * current one from the gyro.
   */
  public abstract void resetOdometry(Pose2d pose, Rotation2d rot);
}
