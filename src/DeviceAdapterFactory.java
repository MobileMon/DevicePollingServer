/**
 * This is a factory which is responsible for taking device types and providing an implementation-specific adapter that
 * can communicate with that device.<br/>
 */
public final class DeviceAdapterFactory {

  /**
   * Given a string description of a device type, locate and create the proper implementation for that type.
   * 
   * @param argType type of device to instantiate. This is the class name for a class that must have a publically
   * accessible default constructor.
   * @return an implementation of <tt>IDevice</tt> appropriate for the type.
   */
  public static IDevice forType(final String argType) {
    final IDevice device;
    if ((argType == null) || argType.trim().isEmpty()) {
      device = new DefaultDevice();
    }
    else {
      try {
        Class<?> type = Class.forName(argType);
        device = (IDevice) type.newInstance();
      }
      catch (Exception ex) {
        throw (ex instanceof RuntimeException) ? (RuntimeException) ex : new RuntimeException(ex);
      }
    }
    return device;
  }

  /** Constructs a <code>DeviceAdapterFactory</code>. */
  private DeviceAdapterFactory() {
    throw new UnsupportedOperationException();
  }

}
