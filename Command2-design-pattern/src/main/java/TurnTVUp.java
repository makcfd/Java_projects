public class TurnTVUp implements Command{

    // create a class that belongs to a specific action of the object
    // so in our case object is TV and action is turnOn
    // we are using a class instead of a method to perform an action

    ElectronicDevice theDevice;

    public TurnTVUp(ElectronicDevice newDevice) {
        theDevice = newDevice;
    }

    @Override
    public void execute() {
        theDevice.volumeUp();
    }

    @Override
    public void undo() {
        theDevice.volumeDown();
    }
}
