package duke;

import duke.tasklist.DoUserCommand;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static boolean isFileEdited = false;
    public static boolean toPrint = true;
    public static int taskCount = 0;

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "duke.txt");
        String absolutePath = path.toString();
        Storage.openFile(absolutePath);
        Ui.doCommandGreet();
        Scanner in = new Scanner(System.in);
        String userCommand;
        do {
            userCommand = in.nextLine();
            DoUserCommand.handleUserCommand(userCommand);
        } while (!userCommand.equals(Ui.COMMAND_BYE));
        try {
            if (isFileEdited) {
                Storage.doEditFile(absolutePath);
            }
        } catch (IOException e) {
            Ui.printFileCreated(false);
        }
    }
}
