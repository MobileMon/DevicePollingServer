package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import server.controller.DeviceManager;

/**
 * This class is the program entry point. It starts the server and pauses the main thread so it does not exit.<br>
 */
public class ServerMain {

  /** Program entry point. */
  public static void main(final String[] args) {
    try (DeviceManager deviceManger = new DeviceManager()) {
      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
      // pause the program so we can see what it is doing.
      stdin.readLine();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
