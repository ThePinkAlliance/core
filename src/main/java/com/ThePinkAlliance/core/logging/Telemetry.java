package com.ThePinkAlliance.core.logging;

import java.util.HashMap;

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

  public static void logData(String title, String data, Class<?> parent) {
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

    currentWidget.getEntry().setString(data);

    layouts.put(id, layout);
    widgets.put(title, currentWidget);
  }

  public static void logData(String title, double data, Class<?> parent) {
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

    currentWidget.getEntry().setDouble(data);

    layouts.put(id, layout);
    widgets.put(title, currentWidget);
  }

  public static void logData(String title, boolean data, Class<?> parent) {
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

    currentWidget.getEntry().setBoolean(data);

    layouts.put(id, layout);
    widgets.put(title, currentWidget);
  }
}
