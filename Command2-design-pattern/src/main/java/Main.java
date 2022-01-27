import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // device
        ElectronicDevice newDevice = TVRemote.getDevice();

        // action
        TurnTVOn onCommand = new TurnTVOn(newDevice);

        // action execution
        DeviceButton onPress = new DeviceButton(onCommand);

        onPress.press();

        // ========================================

        Television theTV = new Television();
        Radio theRadio = new Radio();
        List<ElectronicDevice> allDevices = new ArrayList<ElectronicDevice>();
        allDevices.add(theTV);
        allDevices.add(theRadio);

        // define action
        TurnAllOff doTurnOffDevices = new TurnAllOff(allDevices);

        //action execution
        DeviceButton turnOff = new DeviceButton(doTurnOffDevices);
        turnOff.press();

        theTV.on();
    }
}
