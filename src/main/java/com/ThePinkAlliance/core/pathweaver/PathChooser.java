package com.ThePinkAlliance.core.pathweaver;

import com.ThePinkAlliance.core.selectable.SelectableTrajectory;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import java.util.List;

public class PathChooser {

  private ShuffleboardTab tab;
  private SendableChooser<Trajectory> chooser;
  private List<SelectableTrajectory> selectables;
  private SelectableTrajectory defaultSelectable;

  public PathChooser(String tabName, int columnIndex, int rowIndex) {
    this.chooser = new SendableChooser<Trajectory>();

    this.tab = Shuffleboard.getTab(tabName);
    this.selectables = List.of();

    this.tab.add(this.chooser)
      .withPosition(columnIndex, rowIndex)
      .withSize(2, 1);
  }

  public void registerDefault(SelectableTrajectory selectable) {
    this.defaultSelectable = selectable;

    this.updateChooser(this.selectables);
  }

  public void register(SelectableTrajectory... selectables) {
    this.selectables = List.of(selectables);

    this.updateChooser(this.selectables);
  }

  public Trajectory get() {
    return this.chooser.getSelected();
  }

  private void updateChooser(List<SelectableTrajectory> selectables) {
    for (SelectableTrajectory selectable : selectables) {
      this.chooser.addOption(selectable.getLabel(), selectable.getSelectable());
    }

    if (defaultSelectable != null) this.chooser.setDefaultOption(
        defaultSelectable.getLabel(),
        defaultSelectable.getSelectable()
      );
  }
}
