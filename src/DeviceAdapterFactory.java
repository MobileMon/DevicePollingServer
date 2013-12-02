/**
 * This is a factory which is responsible for taking device types and providing an implementation-specific adapter that
 * can communicate with that device.<br/>
 */
public final class DeviceAdapterFactory {

  /**
   * Given a string description of a device type, locate and create the proper implementation for that type.
   * 
   * @param argType type description provided by the client.
   * @return an implementation of <tt>IDevice</tt> appropriate for the type.
   */
  public static IDevice forType(final String argType) {
    // TODO: add new types. The "Device" class should be the default when a more
    // appropriate type cannot be determined.
    return new Device();
  }

  /** Constructs a <code>DeviceAdapterFactory</code>. */
  private DeviceAdapterFactory() {
    throw new UnsupportedOperationException();
  }

}
