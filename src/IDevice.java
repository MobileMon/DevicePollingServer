import java.io.BufferedReader;
import java.io.IOException;

/**
 * Interface for an object that adapts a device, structuring input and output to be compatible with both the server and
 * the client.
 */
public interface IDevice {

  String getIpAddress();

  void setIpAddress(String argIpAddress);

  int getPortNumber();

  void setPortNumber(int argPortNumber);

  String getDeviceId();

  void setDeviceId(String argDeviceId);

  /**
   * Set data on this object from the input reader.
   * 
   * @param argReader the reader from which to get data.
   * @throws IOException if there is an error setting up the device from the input reader.
   */
  void setUp(BufferedReader argReader) throws IOException;
}
