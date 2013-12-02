import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public class ContainsMain {

  public static void main(final String[] args) throws UnknownHostException, IOException {
    DeviceManager deviceManger = new DeviceManager();
    deviceManger.start();

    // pause the program so we can see what its doing
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    stdin.readLine();

    deviceManger.stop();
  }

}
