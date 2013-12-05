package server.adapter.impl;

import java.io.BufferedReader;
import java.io.IOException;

import server.adapter.IDeviceAdapter;

/**
 * This is the default implementation of a device.
 */
public class DefaultDeviceAdapter
implements IDeviceAdapter {

  private String ipAddress;

  private int portNumber;

  private String deviceId;

  /** {@inheritDoc} */
  @Override
  public String getIpAddress() {
    return this.ipAddress;
  }

  /** {@inheritDoc} */
  @Override
  public int getPortNumber() {
    return this.portNumber;
  }

  /** {@inheritDoc} */
  @Override
  public String getDeviceId() {
    return this.deviceId;
  }

  /** {@inheritDoc} */
  @Override
  public void setUp(final BufferedReader argReader) throws IOException {
    String ip = argReader.readLine();
    int port = Integer.parseInt(argReader.readLine());
    String device = argReader.readLine();
    this.ipAddress = ip;
    this.portNumber = port;
    this.deviceId = device;
  }

  /** {@inheritDoc} */
  @Override
  public int getDeviceStatus(final BufferedReader argReader) throws IOException {
    String statusRaw = argReader.readLine();
    int status = Integer.parseInt(statusRaw);
    return status;
  }

}
