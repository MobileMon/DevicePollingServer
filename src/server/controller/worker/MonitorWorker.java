package server.controller.worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import server.adapter.IDeviceAdapter;
import server.controller.DeviceManager;

/**
 * This is a worker runnable that monitors a single device.
 */
public class MonitorWorker
implements Runnable {

  protected static class TimerExtension
  extends Timer {

    public boolean hasConnected;
  }

  protected final IDeviceAdapter deviceToMonitor;

  protected final DeviceManager deviceManager;

  protected final int howOftenToMonitorInMilliSeconds;

  public MonitorWorker(final IDeviceAdapter argDeviceToMonitor, final DeviceManager deviceManger,
    final int argHowOftenToMonitorInMilliSeconds) {
    this.deviceToMonitor = argDeviceToMonitor;
    this.deviceManager = deviceManger;
    this.howOftenToMonitorInMilliSeconds = argHowOftenToMonitorInMilliSeconds;
  }

  /** {@inheritDoc} */
  @SuppressWarnings("resource")
  @Override
  public void run() {
    // need to check device's status
    while (true) {

      try {
        // sleep until its time to monitor again
        Thread.sleep(this.howOftenToMonitorInMilliSeconds);
      }
      catch (InterruptedException e) {
        System.out.println(e.getMessage());
        break;
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
              MonitorWorker.this.deviceManager.reportDeviceStatus(MonitorWorker.this.deviceToMonitor,
                DeviceManager.DEVICE_STATUS_UNRESPONSIVE);
            }

          }
        }, this.howOftenToMonitorInMilliSeconds);

        socketServer = new Socket(this.deviceToMonitor.getIpAddress(), this.deviceToMonitor.getPortNumber());
        timer.hasConnected = true;
        timer.cancel();
        timer.purge();

      }
      catch (UnknownHostException e) {
        System.out.println("Unknown host " + e.getMessage());
        // if unresponsive
        this.deviceManager.reportDeviceStatus(this.deviceToMonitor, DeviceManager.DEVICE_STATUS_UNRESPONSIVE);
        continue;
      }
      catch (IOException e) {
        System.out.println("IO Exception" + e.getMessage());
        // if unresponsive
        this.deviceManager.reportDeviceStatus(this.deviceToMonitor, DeviceManager.DEVICE_STATUS_UNRESPONSIVE);
        continue;
      }

      BufferedReader in = null;

      try {
        in = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));

        int deviceStatus = this.deviceToMonitor.getDeviceStatus(in);
        // Report the device status. Note that the status must be an integer this method accepts.
        this.deviceManager.reportDeviceStatus(this.deviceToMonitor, deviceStatus);
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
