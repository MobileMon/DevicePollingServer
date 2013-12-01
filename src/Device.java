/**
 * This is the default implementation of a device.
 */
public class Device
implements IDevice {

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

}
