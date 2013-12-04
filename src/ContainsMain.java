import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ContainsMain {

  public static void main(final String[] args) {
    DeviceManager deviceManger = new DeviceManager();
    try {
      deviceManger.start();
      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
      // pause the program so we can see what its doing
      stdin.readLine();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    finally {
      deviceManger.stop();
    }
  }

}
