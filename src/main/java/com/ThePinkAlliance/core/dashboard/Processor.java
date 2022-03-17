package com.ThePinkAlliance.core.dashboard;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

public class Processor extends AbstractProcessor {

  @Override
  public boolean process(
    Set<? extends TypeElement> annotations,
    RoundEnvironment roundEnv
  ) {
    annotations.forEach(
      ann -> {
        Subsystem[] subs = ann.getAnnotationsByType(Subsystem.class);

        for (Subsystem sub : subs) {}
      }
    );

    return false;
  }
}
