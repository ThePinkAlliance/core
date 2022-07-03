package com.ThePinkAlliance.core.selectable;

import edu.wpi.first.wpilibj2.command.Command;

public abstract class SelectableBuilder {

  public static <E extends Object> Selectable<E> build(String name, E object) {
    return new Selectable<E>() {
      @Override
      public String getLabel() {
        return name;
      }

      @Override
      public E getSelectable() {
        return object;
      }
    };
  }

  @Deprecated
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
