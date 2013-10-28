import java.util.HashMap;
import java.util.Random;


public class DeviceManager {
	
	public static final int DEVICE_STATUS_OK = 1;
	public static final int DEVICE_STATUS_UNRESPONSIVE = 2;
	
	private HashMap<String,Device>mapOfDevices;

	public DeviceManager() {
		this.mapOfDevices = new HashMap<String,Device>();
	}
	
	
	public void registerDevice(Device device){
		mapOfDevices.put(device.getDeviceID(),device);
		System.out.println("Device Registered with ID:" + device.getDeviceID() + ", ip address: " + device.getIpAddress()+", port number:" + device.getPortNumber());
	}
	
	public void monitorDevice(String deviceId){
		Device device = mapOfDevices.get(deviceId);
		
		Random r = new Random();
		int whenToMonitor=r.nextInt(60000-20000) + 20000;
		
		System.out.println("Device: "+ device.getDeviceID() + "will be monitored every " + whenToMonitor/1000 + " seconds");
		MonitorWorker monitorWorker = new MonitorWorker(device, this, whenToMonitor); //monitor every 60 seconds
	}
	
	public void reportDeviceStatus(Device device, int deviceStatus){
		if (deviceStatus == DeviceManager.DEVICE_STATUS_OK){
			System.out.println("Device: "+ device.getDeviceID() + " is OK");
		}
		else if (deviceStatus == DeviceManager.DEVICE_STATUS_UNRESPONSIVE){
			System.out.println("Device: "+ device.getDeviceID() + " is unresponsive");
		}
	}
	
	

}
