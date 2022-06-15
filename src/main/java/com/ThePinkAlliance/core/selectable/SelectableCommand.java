package com.ThePinkAlliance.core.selectable;

import edu.wpi.first.wpilibj2.command.Command;

public interface SelectableCommand {
  String getName();

  Command getCommand();
}
