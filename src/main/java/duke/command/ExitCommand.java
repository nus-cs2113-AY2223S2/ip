package duke.command;

import duke.data.DataActions;
import duke.ui.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String BYE_MESSAGE = " Bye! Hope to see you again soon!";
    public static final String MESSAGE_USAGE = " " + COMMAND_WORD + ": exits the program. "
            + Ui.NEW_LINE + "  Example: " + COMMAND_WORD;

    public static void exit(ArrayList<Task> tasks) {
        DataActions.updateSavedData(tasks); // save data to file
        Ui.bye();
    }
}
