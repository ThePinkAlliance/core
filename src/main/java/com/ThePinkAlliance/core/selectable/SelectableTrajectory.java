package com.ThePinkAlliance.core.selectable;

import com.ThePinkAlliance.core.pathweaver.TrajectoryLoader;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;

public class SelectableTrajectory implements Selectable<Trajectory> {

  String name;
  String location;
  Trajectory trajectory;
  SwerveControllerCommand SwerveControllerCommand;

  public SelectableTrajectory(String name, String location) {
    this.location = location;
    this.name = name;

    this.trajectory = TrajectoryLoader.loadFromPath(location);
  }

  @Override
  public String getLabel() {
    return this.name;
  }

  @Override
  public Trajectory getSelectable() {
    return this.trajectory;
  }
}
