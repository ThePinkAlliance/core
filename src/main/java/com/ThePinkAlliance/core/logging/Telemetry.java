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

  /**
   * Logs data to the debug tab on Shuffleboard stored in a list with the name of
   * the parent class.
   * 
   * @param title  Name of the data being logged.
   * @param data   The data object being logged. Permitted types: Strings,
   *               Doubles,
   *               Integers, and Longs.
   * @param parent The parent class object.
   */
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
      currentWidget = layout.add(title, serializeDataType(data));
    }

    if (data instanceof Integer) {
      currentWidget.getEntry().setInteger((long) data);
    } else if (data instanceof String) {
      currentWidget.getEntry().setString((String) data);
    } else if (data instanceof Double) {
      currentWidget.getEntry().setDouble((double) data);
    } else {
      currentWidget.getEntry().setString(data.toString());
    }

    if (layout == null) {
      layouts.put(id, layout);
    }

    widgets.put(title, currentWidget);
  }

  /**
   * Serialize the data type. And convert it into string if it's not a permitted
   * data
   * type (String, Long, Integer, Double).
   */
  private static Object serializeDataType(Object data) {
    boolean validType = data instanceof Integer || data instanceof String || data instanceof Double
        || data instanceof Long;

    if (!validType) {
      return data.toString();
    } else {
      return data;
    }
  }
}
