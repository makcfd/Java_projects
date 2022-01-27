import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Frog frog = new Frog();

        List<FrogCommand> commands = new ArrayList<>();
        int curCommand = -1;
        System.out.println("Initial position of our beloved animal: ");
        showFrogLocation(frog);

        while (true) {
            // getting input data
            Scanner input = new Scanner(System.in);
            System.out.print("\nEnter the command: ");
            String inputCommand = input.nextLine();


            if (inputCommand.equals(">>")) {
                if (curCommand == commands.size() - 1) {
                    System.out.println("No undo command to repeat...");
                } else {
                    curCommand++;
                    commands.get(curCommand).doit();
                }
            } else if (inputCommand.equals("!!")) {
                if (commands.size() == 0) {
                    System.out.println("Nothing to cancel!");
                } else {
                    // repeat the last command
                    // assumption: every command execution considered new command
                    FrogCommand cmd = commands.get(curCommand);
                    cmd.doit();
                    commands.add(cmd);
                    curCommand++;
                }
            } else if (inputCommand.equals("<<")) {
                // undo the last command
                if (curCommand < 0) {
                    System.out.println("Nothing to undo...");
                } else {
                    FrogCommand cmd = commands.get(curCommand);
                    cmd.undo();
                    curCommand--;
                }
            } else if (inputCommand.equals("0")) {
                // exit the game
                System.out.println("*** Game over! ***");
                break;
            } else {
                // frog moves right and left
                int steps = Integer.parseInt(inputCommand);
                if (steps > 0) {
                    curCommand++;
                    FrogCommand cmd = FrogCommands.jumpRightCommand(frog, steps);
                    commands.add(cmd);
                    cmd.doit();

                } else if (steps < 0) {
                    curCommand++;
                    FrogCommand cmd = FrogCommands.jumpLeftCommand(frog, steps);
                    commands.add(cmd);
                    cmd.doit();

                }
            }
//
//            if (/*пользователь хочет отменить действие*/) {
//                if (curCommand < 0) {
//                    System.out.println("Нечего отменять!");
//                } else {
//                    commands.get(curCommand).undo();
//                    curCommand--;
//                }
//            } else if (/*пользователь хочет повторить действие*/) {
//                if (curCommand == commands.size() - 1) {
//                    System.out.println("Нечего отменять!");
//                } else {
//                    curCommand++;
//                    commands.get(curCommand).doit();
//                }
//            } else { //пользователь ввёл новое движение для лягушки
//                if (curCommand != commands.size() - 1) {
//                    //удаляем все команды которые были отменены
//                }
//                FrogCommand cmd = ...
//                curCommand++;
//                commands.add(cmd);
//                cmd.doit();
//            }

            showFrogLocation(frog);
        }
    }

    public static void showFrogLocation(Frog frog) {
        for (int i = 0; i <= 10; i++) {
            if (i == frog.position) {
                System.out.printf("X");
            } else {
                System.out.printf("_");
            }
        }
    }
}
