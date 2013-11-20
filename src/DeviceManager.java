import java.util.HashMap;
import java.util.Random;


public class DeviceManager {
	
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
	
	

}
