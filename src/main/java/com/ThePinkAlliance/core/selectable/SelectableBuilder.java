package com.ThePinkAlliance.core.selectable;

import edu.wpi.first.wpilibj2.command.Command;

public abstract class SelectableBuilder {

  public static CommandSelectable build(String name, Command command) {
    return new CommandSelectable() {
      @Override
      public String getName() {
        return name;
      }

      @Override
      public Command getCommand() {
        return command;
      }
    };
  }
}
