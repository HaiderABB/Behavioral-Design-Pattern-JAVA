import java.util.ArrayList;
import java.util.List;

interface HomeSubject {
  void registerObserver(HomeObserver observer);

  void removeObserver(HomeObserver observer);

  void notifyObservers(Device device, String state);
}

class HomeHub implements HomeSubject {
  private List<HomeObserver> observers = new ArrayList<>();

  @Override
  public void registerObserver(HomeObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(HomeObserver observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers(Device device, String state) {
    for (HomeObserver observer : observers) {
      observer.update(device, state);
    }
  }

  public void deviceStateChanged(Device device, String state) {
    System.out.println(device.getName() + " state changed: " + state);
    notifyObservers(device, state);
  }
}

interface HomeObserver {
  void update(Device device, String state);
}

class SmartApp implements HomeObserver {
  @Override
  public void update(Device device, String state) {
    if (device.getType() == DeviceType.LIGHT) {
      System.out.println("SmartApp: Lights are " + state);
    }
  }
}

class VoiceBot implements HomeObserver {
  @Override
  public void update(Device device, String state) {
    if (device.getType() == DeviceType.THERMOSTAT) {
      System.out.println("Thermostat set to " + state);
    }
  }
}

class SecCam implements HomeObserver {
  @Override
  public void update(Device device, String state) {
    if (device.getType() == DeviceType.CAMERA && state.equals("Motion Detected")) {
      System.out.println("Motion detected!");
    }
  }
}

enum DeviceType {
  LIGHT,
  THERMOSTAT,
  CAMERA
}

class Device {
  private String name;
  private DeviceType type;

  public Device(String name, DeviceType type) {
    this.name = name;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public DeviceType getType() {
    return type;
  }
}

public class HomeAutomation {
  public static void main(String[] args) {
    HomeHub Hub = new HomeHub();

    Device light = new Device("Kitchen Light", DeviceType.LIGHT);
    Device thermostat = new Device("Bedroom Thermostat", DeviceType.THERMOSTAT);
    Device camera = new Device("Living Room  Camera", DeviceType.CAMERA);

    HomeObserver App = new SmartApp();
    HomeObserver Bot = new VoiceBot();
    HomeObserver Cam = new SecCam();

    Hub.registerObserver(App);
    Hub.registerObserver(Bot);
    Hub.registerObserver(Cam);

    Hub.deviceStateChanged(light, "On");
    Hub.deviceStateChanged(thermostat, "72°F");
    Hub.deviceStateChanged(camera, "Motion Detected");
  }
}