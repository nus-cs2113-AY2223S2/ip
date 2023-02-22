package duke.command;

import duke.data.TaskList;
import duke.filemanager.Storage;
import duke.ui.Ui;

/**
 * Exits the program when exit command is entered
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command
     *
     * @param tasks   TaskList of tasks currently stored
     * @param storage Handler to read write to json
     * @param ui      Handler to print text to user
     */
    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) {
        exitProgram();
    }

    /**
     * Prints out the exit message to the user
     * and exits with status 0
     */
    public static void exitProgram() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
        System.exit(0);
    }
}
