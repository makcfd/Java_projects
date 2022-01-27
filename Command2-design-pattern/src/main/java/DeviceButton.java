public class DeviceButton {
    // invoker

    // DeviceButton.press() -> Command.execute() -> execute implemented method like on()/off()/volumeUp()/volumeDown()

    Command theCommand;

    public DeviceButton (Command newCommand) {
        theCommand = newCommand;
    }

    public void press() {
        theCommand.execute();
    }
}
