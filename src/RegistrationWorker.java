import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class RegistrationWorker extends Thread {

	ServerSocket mSocket;
	int mPortNumber = 4000;
	DeviceManager deviceManager;

	public RegistrationWorker(DeviceManager deviceManger) {
		
		
		this.deviceManager = deviceManger;
		
		boolean isPortAvailable = false;
		
		while (!isPortAvailable){

		try {
			mSocket = new ServerSocket(mPortNumber);
			isPortAvailable = true;
		} catch (IOException e) {
		
			mPortNumber++;
			
			if (mPortNumber > 7000){
				System.out.println("Could not open a socket");
				System.exit(-1);
			}
		}
		}
	}

	@Override
	public void run() {

		
		
		
		InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
		}
		System.out.println("Registration Worker IP address : " + ip.getHostAddress());
		System.out.println("Registration Worker Listening on port: "+ mPortNumber);

	
		while (true) {
			
			
			//accept requests
			Socket clientSocket = null;
			
			try {
				clientSocket = mSocket.accept();
				
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Could not connect to socket");
			}
			
			
			
			//read from socket
		/*	try {
	            BufferedReader in = 
	            new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	            String deviceId = in.readLine();
	            deviceId = in.readLine();
	            deviceId = in.readLine();
	            deviceId = in.readLine();
	            deviceId = in.readLine();
	            
	            //TODO: HANDLE REGISTRATION
	            System.out.println("Device Registration ID: " + deviceId);
	           // in.close();
	        } catch (IOException e) {
	            //Client disconnected
	            System.out.println("Client disconnected.");
	            break;
	        }*/
			String line = "";
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String ipAddress = in.readLine();
				int portNumber = Integer.parseInt(in.readLine());
				String deviceID = in.readLine();

				Device device = new Device(ipAddress,portNumber,deviceID);
				deviceManager.registerDevice(device);
				

			} catch (IOException e1) {
				// TODO Auto-generated catch block
			    System.out.println("Read failed");
		        System.exit(-1);
			}
	
			//close client
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	public void cleanUp(){
		
		
		
		
		
		//close socket
		try {
			mSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//stop thread
				try {
					this.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				this.interrupt();
	}
	
	
	
	

}
