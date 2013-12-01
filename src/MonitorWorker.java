import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.TimerTask;

public class MonitorWorker implements Runnable {

  IDevice deviceToMonitor;
  DeviceManager deviceManager;
  int howOftenToMonitorInMilliSeconds;

  public MonitorWorker(IDevice deviceToMonitor, DeviceManager deviceManger, int howOftenToMonitorInMilliSeconds) {
    this.deviceToMonitor = deviceToMonitor;
    this.deviceManager = deviceManger;
    this.howOftenToMonitorInMilliSeconds = howOftenToMonitorInMilliSeconds;
  }

  @Override
  public void run() {
    // need to check device's status
    while (true) {

      try {
        // sleep until its time to monitor again
        Thread.sleep(howOftenToMonitorInMilliSeconds);
      }
      catch (InterruptedException e) {
        System.out.println(e.getMessage());
      }

      // connect to client
      Socket socketServer = null;

      try {
        final TimerExtension timer = new TimerExtension();
        timer.hasConnected = false;
        timer.schedule(new TimerTask() {
          @Override
          public void run() {
            // if device doesn't connect in alloted time it's unresponsive
            if (timer.hasConnected == false) {
              deviceManager.reportDeviceStatus(deviceToMonitor, DeviceManager.DEVICE_STATUS_UNRESPONSIVE);
            }

          }
        }, howOftenToMonitorInMilliSeconds);

        socketServer = new Socket(this.deviceToMonitor.getIpAddress(), this.deviceToMonitor.getPortNumber());
        timer.hasConnected = true;

      }
      catch (UnknownHostException e) {
        System.out.println("Unknown host " + e.getMessage());
        // if unresponsive
        this.deviceManager.reportDeviceStatus(this.deviceToMonitor, DeviceManager.DEVICE_STATUS_UNRESPONSIVE);
      }
      catch (IOException e) {
        System.out.println("IO Exception" + e.getMessage());
        // if unresponsive
        this.deviceManager.reportDeviceStatus(this.deviceToMonitor, DeviceManager.DEVICE_STATUS_UNRESPONSIVE);
      }

      String isDeviceOkay = "";
      BufferedReader in = null;

      try {
        in = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));

        isDeviceOkay = in.readLine();
        int deviceOkayInt = Integer.parseInt(isDeviceOkay);

        if (deviceOkayInt == 1) {

          // if ok
          this.deviceManager.reportDeviceStatus(this.deviceToMonitor, DeviceManager.DEVICE_STATUS_OK);
        }
        else {
          // if not ok
          this.deviceManager.reportDeviceStatus(this.deviceToMonitor, DeviceManager.DEVICE_STATUS_NOT_OKAY);
        }

      }
      catch (Exception e) {
        System.out.println(e.getMessage());
        // if not ok
        this.deviceManager.reportDeviceStatus(this.deviceToMonitor, DeviceManager.DEVICE_STATUS_NOT_OKAY);

      }

      try {
        socketServer.close();
      }
      catch (IOException e) {
        System.out.println(e.getMessage());
      }

    }

  }
}
