package server.adapter;

import server.adapter.impl.DefaultDeviceAdapter;

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
  public static IDeviceAdapter forType(final String argType) {
    final IDeviceAdapter device;
    if ((argType == null) || argType.trim().isEmpty()) {
      device = new DefaultDeviceAdapter();
    }
    else {
      try {
        Class<?> type = Class.forName(argType);
        device = (IDeviceAdapter) type.newInstance();
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
