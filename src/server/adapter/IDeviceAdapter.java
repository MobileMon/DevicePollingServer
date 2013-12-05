package server.adapter;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Interface for an object that adapts a device, structuring input and output to be compatible with both the server and
 * the client.
 */
public interface IDeviceAdapter {

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

  /**
   * Get the device status. It will be one of the constants on the device manager class.
   * 
   * @param argReader an object which can read strings from the client device.
   * @return an integer constant describing the status.
   * @throws IOException if there is a communication error.
   */
  int getDeviceStatus(BufferedReader argReader) throws IOException;
}
