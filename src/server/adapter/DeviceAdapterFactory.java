package server.adapter;

import java.util.concurrent.atomic.AtomicBoolean;

import server.adapter.impl.DefaultDeviceAdapter;

/**
 * This is a factory which is responsible for taking device types and providing an implementation-specific adapter that
 * can communicate with that device.<br/>
 */
public final class DeviceAdapterFactory {

  /** Used to track whether the singleton has already been created. */
  private static final AtomicBoolean instantiated = new AtomicBoolean();

  private static final DeviceAdapterFactory instance;

  static {
    try {
      String implName = System.getProperty(DeviceAdapterFactory.class.getName());
      if ((implName == null) || implName.trim().isEmpty()) {
        instance = new DeviceAdapterFactory();
      }
      else {
        Class<?> impl = Class.forName(implName);
        instance = (DeviceAdapterFactory) impl.newInstance();
      }
    }
    catch (Exception ex) {
      throw new ExceptionInInitializerError(ex);
    }
  }

  /** Get the singleton instance of the factory. */
  public static DeviceAdapterFactory getInstance() {
    return instance;
  }

  /** Constructs a <code>DeviceAdapterFactory</code>. */
  protected DeviceAdapterFactory() {
    if (!instantiated.compareAndSet(false, true)) {
      throw new IllegalStateException("Cannot create multiple singletons");
    }
  }

  /**
   * Given a string description of a device type, locate and create the proper implementation for that type.
   * 
   * @param argType type of device to instantiate. This is the class name for a class that must have a publically
   * accessible default constructor.
   * @return an implementation of <tt>IDevice</tt> appropriate for the type.
   */
  public IDeviceAdapter makeAdapter(final String argType) {
    final IDeviceAdapter deviceAdapter;
    if ((argType == null) || argType.trim().isEmpty()) {
      deviceAdapter = new DefaultDeviceAdapter();
    }
    else {
      try {
        Class<?> type = Class.forName(argType);
        deviceAdapter = (IDeviceAdapter) type.newInstance();
      }
      catch (Exception ex) {
        throw (ex instanceof RuntimeException) ? (RuntimeException) ex : new RuntimeException(ex);
      }
    }
    return deviceAdapter;
  }

}
