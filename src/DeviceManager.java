import java.util.ArrayList;


public class DeviceManager {
	private ArrayList<Device>listOfDevices;

	public DeviceManager() {
		this.listOfDevices = new ArrayList<Device>();
	}
	
	
	public void registerDevice(Device device){
		listOfDevices.add(device);
		System.out.println("Device Registered with ID:" + device.getDeviceID() + ", ip address: " + device.getIpAddress()+", port number:" + device.getPortNumber());
	}
	
	

}
