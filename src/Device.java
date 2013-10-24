
public class Device {
	
	private String ipAddress;
	private int portNumber;
	private String deviceID;
	
	
	
	
	
	public Device(String ipAddress, int portNumber, String deviceID) {
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
		this.deviceID = deviceID;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

}
