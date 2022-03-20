package com.ThePinkAlliance.core.dashboard;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public interface DashboardEntry {
  Supplier<Object> stream = () -> 0;
  String name = "unknown";
}
