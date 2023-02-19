package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand {
    public static final String COMMAND_WORD = "bye";
    public static final String BYE_MESSAGE = " Bye! Hope to see you again soon!";
    public static final String MESSAGE_USAGE = " " + COMMAND_WORD + ": exits the program. "
            + Ui.NEW_LINE + "  Example: " + COMMAND_WORD;

    public static void exit(TaskList taskList, Ui ui) {
        Storage.updateSavedData(taskList); // save data to file
        ui.byeMessage();
    }
}
