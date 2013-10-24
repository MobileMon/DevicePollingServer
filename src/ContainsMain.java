import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;



public class ContainsMain {
	
	
	static DeviceManager deviceManger;


	public static void main(String[] args) throws UnknownHostException, IOException {

		deviceManger = new DeviceManager();
		
		
		
		//this worker will accept registration requests
		RegistrationWorker registrationWorker = new RegistrationWorker(deviceManger);
		registrationWorker.start();
		
		
		
		
		
		// pause the program so we can see what its doing
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in)); 
		stdin.readLine();
		
		registrationWorker.cleanUp();
	}
	
	

	

}
