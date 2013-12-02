import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The device manager is responsible for handling device registration and maintaining devices as they connect and
 * disconnect.
 */
public class DeviceManager {
<<<<<<< HEAD
	
	public static final int DEVICE_STATUS_OK = 1;
	public static final int DEVICE_STATUS_UNRESPONSIVE = 2;
	public static final int DEVICE_STATUS_NOT_OKAY = 3;
	
	private HashMap<String,Device>mapOfDevices;

	public DeviceManager() {
		this.mapOfDevices = new HashMap<String,Device>();
	}
	
	
	public void registerDevice(Device device){
		
		if (mapOfDevices.get(device.getDeviceID()) != null){
			System.out.println("Not registering because device already registered!");
		}
		else{
			mapOfDevices.put(device.getDeviceID(),device);
			System.out.println("Device Registered with ID:" + device.getDeviceID() + ", ip address: " + device.getIpAddress()+", port number:" + device.getPortNumber());
			this.monitorDevice(device.getDeviceID());
		}

	}
	
	public void monitorDevice(String deviceId){
		Device device = mapOfDevices.get(deviceId);
		
		Random r = new Random();
		//int whenToMonitor=r.nextInt(60000-20000) + 20000;
		int whenToMonitor = r.nextInt(10000-1000) + 1000;
		
		System.out.println("Device: "+ device.getDeviceID() + " will be monitored every " + whenToMonitor/1000 + " seconds");
		MonitorWorker monitorWorker = new MonitorWorker(device, this, whenToMonitor); //monitor between 20 and 60 seconds
		monitorWorker.start();
	}
	
	public void reportDeviceStatus(Device device, int deviceStatus){
		if (deviceStatus == DeviceManager.DEVICE_STATUS_OK){
			System.out.println("Device: "+ device.getDeviceID() + " is OK");
		}
		else if (deviceStatus == DeviceManager.DEVICE_STATUS_UNRESPONSIVE){
			System.out.println("Device: "+ device.getDeviceID() + " is unresponsive");
		}
		else if (deviceStatus == DeviceManager.DEVICE_STATUS_NOT_OKAY){
			System.out.println("Device: "+ device.getDeviceID() + " is not okay");
		}
	}
	
	
=======

  public static final int DEVICE_STATUS_OK = 1;

  public static final int DEVICE_STATUS_UNRESPONSIVE = 2;

  public static final int DEVICE_STATUS_NOT_OKAY = 3;

  private final HashMap<String, IDevice> devices = new HashMap<String, IDevice>();

  private final ExecutorService registrationExec = Executors.newSingleThreadExecutor();

  private final ExecutorService monitors = Executors.newCachedThreadPool();

  /** Start managing devices. This should only ever be called once per manager, and only before it is stopped. */
  public void start() {
    this.registrationExec.submit(new RegistrationWorker(this));
  }

  /** Stop managing devices. This should only ever be called once per manager, and only after it is started. */
  public void stop() {
    this.registrationExec.shutdown();
    this.monitors.shutdown();
  }

  public void registerDevice(final IDevice device) {
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
    IDevice device = this.devices.get(deviceId);

    Random r = new Random();
    // int whenToMonitor=r.nextInt(60000-20000) + 20000;
    int whenToMonitor = r.nextInt(10000 - 1000) + 1000;

    System.out.println("Device: " + device.getDeviceId() + " will be monitored every " + (whenToMonitor / 1000)
      + " seconds");
    MonitorWorker monitorWorker = new MonitorWorker(device, this, whenToMonitor); // monitor
                                                                                  // between
                                                                                  // 20
                                                                                  // and
                                                                                  // 60
                                                                                  // seconds
    this.monitors.submit(monitorWorker);
  }

  public void reportDeviceStatus(final IDevice device, final int deviceStatus) {
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
>>>>>>> origin/john

}
