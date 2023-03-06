package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Terminates the program
 */
public class ExitCommand {

    public static final String COMMAND_WORD = "bye";

    public static final String BYE_MESSAGE = " Bye! Hope to see you again soon!";

    public static final String MESSAGE_USAGE = " " + COMMAND_WORD + ": exits the program. "
            + Ui.NEW_LINE + "  Example: " + COMMAND_WORD;

    /**
     * Saves the data in task list into file and shows bye message
     *
     * @param taskList task list containing the tasks
     * @param ui       to be used for interaction with user
     * @param storage  to be used for saving of tasks in task list into file
     */
    public static void exit(TaskList taskList, Ui ui, Storage storage) {
        storage.updateSavedData(taskList); // save data to file
        ui.byeMessage();
    }
}
