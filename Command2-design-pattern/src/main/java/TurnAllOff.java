import java.util.List;

public class TurnAllOff implements Command{

    List<ElectronicDevice> AllDevices;

    public TurnAllOff(List<ElectronicDevice> newDevices) {
        AllDevices = newDevices;
    }

    @Override
    public void execute() {
        for (ElectronicDevice device: AllDevices) {
            device.off();
        }

    }
}
