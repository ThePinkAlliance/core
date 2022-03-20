package com.ThePinkAlliance.core.dashboard;

import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DashboardSubsystem extends SubsystemBase {

  private ShuffleboardTab tab;
  private InfoThread infoThread;
  private Thread runningThread;
  private ArrayList<DashboardEntry> entries = new ArrayList<>();

  public DashboardSubsystem(String name, DashboardEntry... entries) {
    this.tab = Shuffleboard.getTab(name);
    this.entries = new ArrayList<DashboardEntry>(List.of(entries));
    this.infoThread = new InfoThread(tab, this.entries);
    this.runningThread = new Thread(infoThread);

    runningThread.start();
  }

  public void createEntry(DashboardEntry entry) {
    infoThread.add(entry);
  }
}

class InfoThread implements Runnable {

  private ArrayList<DashboardEntry> entries = new ArrayList<>();
  private HashMap<String, SimpleWidget> widgets = new HashMap<>();
  private ShuffleboardTab tab;

  public InfoThread(ShuffleboardTab tab, ArrayList<DashboardEntry> entries) {
    this.entries = entries;
    this.tab = tab;
  }

  @Override
  public void run() {
    populateWidgets(entries);

    updateEntries(entries);
  }

  @SuppressWarnings("static-access")
  private void updateEntries(ArrayList<DashboardEntry> entries) {
    entries.forEach(
      e -> {
        SimpleWidget currentWidget = widgets.get(e.name);

        currentWidget.getEntry().forceSetValue(e.stream.get());
      }
    );
  }

  @SuppressWarnings("static-access")
  private void populateWidgets(ArrayList<DashboardEntry> entries) {
    entries.forEach(
      e -> {
        Objects.requireNonNull(e, "Dashboard Entries cannot be null");

        String name = e.name;

        if (widgets.get(name) == null) {
          widgets.put(name, tab.add(name, e.stream.get()));
        }
      }
    );
  }

  public void add(DashboardEntry entry) {
    this.entries.add(entry);
  }
}
