/**
 * This is a factory which is responsible for taking device types and providing
 * an implementation-specific adapter that can communicate with that device.<br/>
 */
public final class DeviceAdapterFactory {

  public static IDevice forType(String argType) {
    // TODO: add new types. The "Device" class should be the default when a more
    // appropriate type cannot be determined.
    return new Device();
  }

  private DeviceAdapterFactory() {
    throw new UnsupportedOperationException();
  }

}
