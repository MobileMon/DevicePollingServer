import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeviceManager {

  public static final int DEVICE_STATUS_OK = 1;
  public static final int DEVICE_STATUS_UNRESPONSIVE = 2;
  public static final int DEVICE_STATUS_NOT_OKAY = 3;

  private HashMap<String, IDevice> mapOfDevices;

  private final ExecutorService registrationExec = Executors.newSingleThreadExecutor();
  private final ExecutorService monitors = Executors.newCachedThreadPool();

  public DeviceManager() {
    this.mapOfDevices = new HashMap<String, IDevice>();
    registrationExec.submit(new RegistrationWorker(this));
  }

  public void registerDevice(IDevice device) {
    if (mapOfDevices.get(device.getDeviceId()) != null) {
      System.out.println("Not registering because device already registered!");
    }
    else {
      mapOfDevices.put(device.getDeviceId(), device);
      System.out.println("Device Registered with ID:" + device.getDeviceId() + ", ip address: " + device.getIpAddress()
          + ", port number:" + device.getPortNumber());
      this.monitorDevice(device.getDeviceId());
    }
  }

  public void monitorDevice(String deviceId) {
    IDevice device = mapOfDevices.get(deviceId);

    Random r = new Random();
    // int whenToMonitor=r.nextInt(60000-20000) + 20000;
    int whenToMonitor = r.nextInt(10000 - 1000) + 1000;

    System.out.println("Device: " + device.getDeviceId() + " will be monitored every " + whenToMonitor / 1000
        + " seconds");
    MonitorWorker monitorWorker = new MonitorWorker(device, this, whenToMonitor); // monitor
                                                                                  // between
                                                                                  // 20
                                                                                  // and
                                                                                  // 60
                                                                                  // seconds
    monitors.submit(monitorWorker);
  }

  public void reportDeviceStatus(IDevice device, int deviceStatus) {
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
