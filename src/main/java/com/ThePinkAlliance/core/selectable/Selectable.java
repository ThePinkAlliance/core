package com.ThePinkAlliance.core.selectable;

public interface Selectable<T extends Object> {
  String getLabel();
  T getSelectable();
}
