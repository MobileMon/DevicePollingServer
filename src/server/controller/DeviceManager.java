package server.controller;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.adapter.IDeviceAdapter;
import server.controller.worker.MonitorWorker;
import server.controller.worker.RegistrationWorker;

/**
 * The device manager is responsible for handling device registration and maintaining devices as they connect and
 * disconnect.
 */
public class DeviceManager
implements AutoCloseable {

  public static final int DEVICE_STATUS_OK = 1;

  public static final int DEVICE_STATUS_UNRESPONSIVE = 2;

  public static final int DEVICE_STATUS_NOT_OKAY = 3;

  private final HashMap<String, IDeviceAdapter> devices = new HashMap<String, IDeviceAdapter>();

  private final ExecutorService registrationExec = Executors.newSingleThreadExecutor();

  private final ExecutorService monitors = Executors.newCachedThreadPool();

  /** Constructs a <code>DeviceManager</code> and start managing devices.. */
  public DeviceManager() {
    this.registrationExec.submit(new RegistrationWorker(this));
  }

  /** {@inheritDoc} */
  @Override
  public void close() {
    this.registrationExec.shutdown();
    this.monitors.shutdown();
  }

  public void registerDevice(final IDeviceAdapter device) {
    if (this.devices.get(device.getDeviceId()) != null) {
      System.out.println("Not registering because device already registered!");
    }
    else {
      this.devices.put(device.getDeviceId(), device);
      System.out.println("Device Registered with ID:" + device.getDeviceId() + ", ip address: " + device.getIpAddress()
        + ", port number:" + device.getPortNumber());
      monitorDevice(device.getDeviceId());
    }
  }

  public void monitorDevice(final String deviceId) {
    IDeviceAdapter device = this.devices.get(deviceId);

    Random r = new Random();
    // int whenToMonitor=r.nextInt(60000-20000) + 20000;
    int whenToMonitor = r.nextInt(10000 - 1000) + 1000;

    System.out.println("Device: " + device.getDeviceId() + " will be monitored every " + (whenToMonitor / 1000)
      + " seconds");
    MonitorWorker monitorWorker = new MonitorWorker(device, this, whenToMonitor); // monitor
                                                                                  // between
                                                                                  // 1
                                                                                  // and
                                                                                  // 10
                                                                                  // seconds
    this.monitors.submit(monitorWorker);
  }

  public void reportDeviceStatus(final IDeviceAdapter device, final int deviceStatus) {
    if (deviceStatus == DeviceManager.DEVICE_STATUS_OK) {
      System.out.println("Device: " + device.getDeviceId() + " is OK");
    }
    else if (deviceStatus == DeviceManager.DEVICE_STATUS_UNRESPONSIVE) {
      System.out.println("Device: " + device.getDeviceId() + " is unresponsive");
    }
    else if (deviceStatus == DeviceManager.DEVICE_STATUS_NOT_OKAY) {
      System.out.println("Device: " + device.getDeviceId() + " is not okay");
    }
  }

}
