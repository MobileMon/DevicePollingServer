package server.controller.worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import server.adapter.DeviceAdapterFactory;
import server.adapter.IDeviceAdapter;
import server.controller.DeviceManager;

/**
 * This is a worker runnable that handles new registrations.
 */
public class RegistrationWorker
implements Runnable {

  protected ServerSocket mSocket;

  protected int mPortNumber = 4000;

  protected final DeviceManager deviceManager;

  public RegistrationWorker(final DeviceManager deviceManger) {
    this.deviceManager = deviceManger;
    boolean isPortAvailable = false;
    while (!isPortAvailable) {
      try {
        this.mSocket = new ServerSocket(this.mPortNumber);
        isPortAvailable = true;
      }
      catch (IOException e) {
        this.mPortNumber++;
        if (this.mPortNumber > 7000) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public void run() {
    try {
      InetAddress ip = null;
      try {
        ip = InetAddress.getLocalHost();
      }
      catch (UnknownHostException e1) {
        // Should never occur.
        e1.printStackTrace();
        return;
      }
      System.out.println("Registration Worker IP address : " + ip.getHostAddress());
      System.out.println("Registration Worker Listening on port: " + this.mPortNumber);

      while (true) {
        // accept requests
        try (Socket clientSocket = this.mSocket.accept()) {
          try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            // TODO: need to add a device type to the protocol as the first line.
            // Pass that into the adapter factory call.
            IDeviceAdapter device = DeviceAdapterFactory.makeAdapter(null);
            device.setUp(in);
            this.deviceManager.registerDevice(device);
          }
          catch (IOException e1) {
            System.out.println("Read failed");
            e1.printStackTrace();
          }
        }
        catch (IOException e) {
          System.out.println("Could not connect to socket");
          e.printStackTrace();
        }
      }
    }
    finally {
      // close socket
      try {
        this.mSocket.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
