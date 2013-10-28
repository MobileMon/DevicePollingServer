
public class MonitorWorker extends Thread{
	
	Device deviceToMonitor;
	DeviceManager deviceManager;
	int howOftenToMonitorInMilliSeconds;
	
	public MonitorWorker(Device deviceToMonitor, DeviceManager deviceManger, int howOftenToMonitorInMilliSeconds){
		this.deviceToMonitor = deviceToMonitor;
		this.deviceManager = deviceManger;
		this.howOftenToMonitorInMilliSeconds = howOftenToMonitorInMilliSeconds;
	}
	

	
	@Override
	public void run() {
		//need to check device's status
		while (true){
			
			//if ok
			this.deviceManager.reportDeviceStatus(this.deviceToMonitor, DeviceManager.DEVICE_STATUS_OK);
			
			//if unresponsive
			this.deviceManager.reportDeviceStatus(this.deviceToMonitor, DeviceManager.DEVICE_STATUS_UNRESPONSIVE);
			
			try {
				//sleep until its time to monitor again
				Thread.sleep(howOftenToMonitorInMilliSeconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
}
