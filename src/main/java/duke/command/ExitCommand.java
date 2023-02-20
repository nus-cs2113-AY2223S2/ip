package duke.command;

import duke.data.TaskList;
import duke.filemanager.Storage;
import duke.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) {
        exitProgram();
    }

    /**
     * Prints out the exit message to the user
     */
    public static void exitProgram() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
        System.exit(0);
    }
}
