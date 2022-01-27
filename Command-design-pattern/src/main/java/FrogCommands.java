public class FrogCommands {

    // returning action/command as an object instance, which
    // if called .doit(), frog will execute this action,
    // if called .undo(), frog will returned to previous state

    public static FrogCommand jumpRightCommand(Frog frog, int steps) {
        FrogCommand action = new FrogCommand() {
            @Override
            public boolean doit() {
                return frog.jump(steps);
            }

            @Override
            public boolean undo() {
                return frog.jump(steps*(-1));
            }
        };
        return action;
    }

    public static FrogCommand jumpLeftCommand(Frog frog, int steps) {
        FrogCommand action = new FrogCommand() {
            @Override
            public boolean doit() {
                return frog.jump(steps);
            }

            @Override
            public boolean undo() {
                return frog.jump(steps*(-1));
            }
        };
        return action;
    }
}