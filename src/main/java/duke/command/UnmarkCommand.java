package duke.command;

import duke.error.DukeException;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE = " OK, I've marked this task as not done yet:";
    public static final String MESSAGE_USAGE = " " + COMMAND_WORD + ": marks a task from the task list as not done. "
            + Ui.NEW_LINE + "  Parameters: task number"
            + Ui.NEW_LINE + "  Example: " + COMMAND_WORD + " 1";

    public static void unmarkTask(ArrayList<Task> tasks, String[] arrayOfInput) throws DukeException {
        int taskNumber = Integer.parseInt(Parser.parseCommand(arrayOfInput, COMMAND_WORD)) - 1;
        tasks.get(taskNumber).markAsNotDone();
        Ui.markOrUnmark(tasks, taskNumber);
    }
}
