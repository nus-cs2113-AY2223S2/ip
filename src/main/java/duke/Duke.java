package duke;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {

    public static boolean isFileEdited = false;
    public static boolean toPrint = true;
    public static int taskCount = 0;

    public static void main(String[] args) {

        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "duke.txt");
        String absolutePath = path.toString();
        Storage.openFile(absolutePath);

        Ui.printGreetMessage();
        String userCommand;

        do {
            userCommand = Ui.getUserInput();
            TaskList.handleUserCommand(userCommand);
        } while (!userCommand.equals(TaskList.COMMAND_BYE));
        try {
            if (isFileEdited) {
                Storage.doEditFile(absolutePath);
            }
        } catch (IOException e) {
            Ui.printFileCreated(false);
        }
    }
}
