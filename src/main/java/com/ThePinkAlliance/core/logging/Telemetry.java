package com.ThePinkAlliance.core.logging;

import java.util.HashMap;

import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardContainer;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;

public class Telemetry {
  private static ShuffleboardContainer tab = Shuffleboard.getTab("debug");
  private static HashMap<Integer, ShuffleboardLayout> layouts = new HashMap<>();
  private static HashMap<String, SimpleWidget> widgets = new HashMap<>();

  public Telemetry() {
  }

  public static void logData(String title, Object data, Class<?> parent) {
    Class<?> superClass = parent.getSuperclass();
    String className = parent.getSimpleName();
    String superClassName = superClass != null ? superClass.getSimpleName() : "Unknown";
    int id = (className + superClassName).hashCode();
    ShuffleboardLayout layout = layouts.get(id);
    SimpleWidget currentWidget = widgets.get(title);

    if (layout == null) {
      layout = tab.getLayout(className + " :: " + superClassName, "List");
    }

    if (currentWidget == null) {
      currentWidget = layout.add(title, data);
    }

    if (data instanceof Integer) {
      currentWidget.getEntry().setInteger((long) data);
    } else if (data instanceof String) {
      currentWidget.getEntry().setString((String) data);
    } else if (data instanceof Double) {
      currentWidget.getEntry().setDouble((double) data);
    } else {
      currentWidget.getEntry().setString((String) data);
    }

    layouts.put(id, layout);
    widgets.put(title, currentWidget);
  }
}
