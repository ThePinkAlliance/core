package com.ThePinkAlliance.core.selectable;

import edu.wpi.first.wpilibj2.command.Command;

public abstract class SelectableBuilder {

  public static Selectable build(String name, Object object) {
    return new Selectable() {
      @Override
      public String getLabel() {
        return name;
      }

      @Override
      public Object getSelectable() {
        return object;
      }
    };
  }

  public static SelectableCommand build(String name, Command command) {
    return new SelectableCommand() {
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
