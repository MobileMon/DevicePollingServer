import java.util.HashMap;


public class DeviceManager {
	private HashMap<String,Device>mapOfDevices;

	public DeviceManager() {
		this.mapOfDevices = new HashMap<String,Device>();
	}
	
	
	public void registerDevice(Device device){
		mapOfDevices.put(device.getDeviceID(),device);
		System.out.println("Device Registered with ID:" + device.getDeviceID() + ", ip address: " + device.getIpAddress()+", port number:" + device.getPortNumber());
	}
	
	//TODO: need to add device monitor
	
	

}
