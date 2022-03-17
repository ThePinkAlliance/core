package com.ThePinkAlliance.core.dashboard;

public class DashboardHandler implements Runnable {

  Entry[] entries;

  public DashboardHandler(Entry[] entries) {
    this.entries = entries;
  }

  public Entry[] setEntries(Entry[] entries) {
    this.entries = entries;

    return this.entries;
  }

  @Override
  public void run() {}
}
