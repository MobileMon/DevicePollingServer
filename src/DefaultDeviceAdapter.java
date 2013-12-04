import java.io.BufferedReader;
import java.io.IOException;

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
  public void setIpAddress(final String argIpAddress) {
    this.ipAddress = argIpAddress;
  }

  /** {@inheritDoc} */
  @Override
  public int getPortNumber() {
    return this.portNumber;
  }

  /** {@inheritDoc} */
  @Override
  public void setPortNumber(final int argPortNumber) {
    this.portNumber = argPortNumber;
  }

  /** {@inheritDoc} */
  @Override
  public String getDeviceId() {
    return this.deviceId;
  }

  /** {@inheritDoc} */
  @Override
  public void setDeviceId(final String argDeviceId) {
    this.deviceId = argDeviceId;
  }

  /** {@inheritDoc} */
  @Override
  public void setUp(final BufferedReader argReader) throws IOException {
    String ip = argReader.readLine();
    int port = Integer.parseInt(argReader.readLine());
    String device = argReader.readLine();
    setIpAddress(ip);
    setPortNumber(port);
    setDeviceId(device);
  }

}
