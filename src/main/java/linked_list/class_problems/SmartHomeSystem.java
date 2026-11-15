import java.util.LinkedHashMap;
import java.util.Map;

public class SmartHomeSystem {

    enum DeviceType {
        LIGHT, FAN, PLUG
    }

    static class Device {
        String id;
        DeviceType type;
        boolean isOn;
        int dimLevel;
        int scheduledTime;
        double energyKWh;

        Device(String id, DeviceType type, double energyKWh) {
            this.id = id;
            this.type = type;
            this.isOn = false;
            this.dimLevel = 100;
            this.scheduledTime = -1;
            this.energyKWh = energyKWh;
        }

        boolean supportsDim()      { return type == DeviceType.LIGHT; }
        boolean supportsSchedule() { return type == DeviceType.LIGHT || type == DeviceType.FAN; }
        boolean supportsEnergy()   { return type == DeviceType.PLUG; }
    }

    private Map<String, Device> devices = new LinkedHashMap<>();

    public void addDevice(Device device) {
        devices.put(device.id, device);
    }

    public void processCommand(String command) {
        String[] parts = command.trim().split("\\s+");
        String cmd      = parts[0];
        String deviceId = parts[1];
        Device device   = devices.get(deviceId);

        if (device == null) {
            System.out.println(deviceId + " not found");
            return;
        }

        switch (cmd) {
            case "ON":
                device.isOn = true;
                System.out.println(deviceId + " is ON");
                break;

            case "OFF":
                device.isOn = false;
                System.out.println(deviceId + " is OFF");
                break;

            case "DIM":
                if (!device.supportsDim()) {
                    System.out.println(deviceId + " rejected DIM unsupported");
                } else {
                    device.dimLevel = Integer.parseInt(parts[2]);
                    System.out.println(deviceId + " dimmed to " + device.dimLevel);
                }
                break;

            case "SCHEDULE":
                if (!device.supportsSchedule()) {
                    System.out.println(deviceId + " rejected SCHEDULE unsupported");
                } else {
                    device.scheduledTime = Integer.parseInt(parts[2]);
                    System.out.println(deviceId + " scheduled " + device.scheduledTime);
                }
                break;

            case "ENERGY":
                if (!device.supportsEnergy()) {
                    System.out.println(deviceId + " rejected ENERGY unsupported");
                } else {
                    System.out.println(deviceId + " energy " + (int) device.energyKWh + " kWh");
                }
                break;

            default:
                System.out.println("Unknown command: " + cmd);
        }
    }

    public static void main(String[] args) {
        SmartHomeSystem system = new SmartHomeSystem();

        // Setup devices: L1 = light (dim + schedule), F1 = fan (schedule), P1 = plug (energy 12 kWh)
        system.addDevice(new Device("L1", DeviceType.LIGHT, 0));
        system.addDevice(new Device("F1", DeviceType.FAN,   0));
        system.addDevice(new Device("P1", DeviceType.PLUG,  12));

        // Process commands
        system.processCommand("ON L1");
        system.processCommand("DIM L1 40");
        system.processCommand("DIM F1 30");
        system.processCommand("SCHEDULE F1 2200");
        system.processCommand("ENERGY P1");
        system.processCommand("ENERGY L1");
    }
}
