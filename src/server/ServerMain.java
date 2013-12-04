package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import server.controller.DeviceManager;

/**
 * This class is the program entry point. It starts the server and pauses the main thread so it does not exit.<br>
 */
public class ServerMain {

  public static void main(final String[] args) {
    DeviceManager deviceManger = new DeviceManager();
    try {
      deviceManger.start();
      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
      // pause the program so we can see what its doing
      stdin.readLine();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    finally {
      deviceManger.stop();
    }
  }

}
