package com.ThePinkAlliance.core.dashboard;

public class Entry {

  private String table;
  private Object data;

  public Entry(String table, Object data) {
    this.data = data;
    this.table = table;
  }

  public Entry set(String table, Object data) {
    this.data = data;
    this.table = table;

    return this;
  }

  public Entry get() {
    return this;
  }
}
