import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public class ContainsMain {

  @SuppressWarnings("unused")
  public static void main(String[] args) throws UnknownHostException, IOException {
    DeviceManager deviceManger = new DeviceManager();

    // pause the program so we can see what its doing
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    stdin.readLine();
  }

}
