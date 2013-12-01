/**
 * Interface for an object that adapts a device, structuring input and output to
 * be compatible with both the server and the client.
 */
public interface IDevice {
  String getIpAddress();

  void setIpAddress(String ipAddress);

  int getPortNumber();

  void setPortNumber(int portNumber);

  String getDeviceId();

  void setDeviceId(String deviceId);
}
