package com.ThePinkAlliance.core.logging;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardContainer;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;

public class Telemetry {
  private static ShuffleboardContainer tab;
  private static HashMap<Integer, ShuffleboardLayout> layouts;
  private static HashMap<String, SimpleWidget> widgets;

  public Telemetry() {
    tab = Shuffleboard.getTab("debug");
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
}
