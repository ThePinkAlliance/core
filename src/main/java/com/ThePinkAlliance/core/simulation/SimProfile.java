package com.ThePinkAlliance.core.simulation;

/**
 * Holds information about a simulated device.
 */
public class SimProfile {

  private long _lastTime;
  private boolean _running = false;

  /**
   * Runs the simulation profile.
   * Implemented by device-specific profiles.
   */
  public void run() {
  }

  /**
   * Returns the time since last call, in milliseconds.
   */
  protected double getPeriod() {
    // set the start time if not yet running
    if (!_running) {
      _lastTime = System.nanoTime();
      _running = true;
    }

    long now = System.nanoTime();
    final double period = (now - _lastTime) / 1000000.;
    _lastTime = now;

    return period;
  }
}