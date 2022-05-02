package com.ThePinkAlliance.core.pathweaver;

import com.ThePinkAlliance.core.selectable.CommandSelectable;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.List;

public class PathChooser {

  private ShuffleboardTab tab;
  private SendableChooser<Command> chooser;
  private List<CommandSelectable> selectables;
  private CommandSelectable defaultSelectable;

  public PathChooser(String tabName, int columnIndex, int rowIndex) {
    this.chooser = new SendableChooser<Command>();

    this.tab = Shuffleboard.getTab(tabName);
    this.selectables = List.of();

    this.tab.add(this.chooser)
      .withPosition(columnIndex, rowIndex)
      .withSize(2, 1);
  }

  public void registerDefault(CommandSelectable selectable) {
    this.defaultSelectable = selectable;

    this.updateChooser(this.selectables);
  }

  public void register(CommandSelectable... selectables) {
    this.selectables = List.of(selectables);

    this.updateChooser(this.selectables);
  }

  private void updateChooser(List<CommandSelectable> selectables) {
    for (CommandSelectable selectable : selectables) {
      this.chooser.addOption(selectable.getName(), selectable.getCommand());
    }

    if (defaultSelectable != null) this.chooser.setDefaultOption(
        defaultSelectable.getName(),
        defaultSelectable.getCommand()
      );
  }
}
