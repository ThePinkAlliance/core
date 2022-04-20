package com.ThePinkAlliance.core.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class CommandLogger {

  private String commandName;
  private String filename;
  private String directory = "/home/lvuser/pink/logger/";

  public CommandLogger(String commandName) {
    this.commandName = commandName;
    this.filename = commandName + ".command.csv";
  }

  public void recordWithTime(String[] rows, Object... objects) {
    String timestamp = new Timestamp(new Date().getTime()).toString();
    File file = new File(
      directory + commandName + "-" + timestamp + ".command.csv"
    );
    FileWriter writer;

    try {
      writer = new FileWriter(file);

      if (!file.exists()) {
        file.createNewFile();
      }
    } catch (IOException err) {
      err.printStackTrace();
    }
  }
}
