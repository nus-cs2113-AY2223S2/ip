package duke.command;

import duke.error.DukeException;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE = " Awesome! I've marked this task as done:";
    public static final String MESSAGE_USAGE = " " + COMMAND_WORD + ": marks a task from the task list as done. "
            + Ui.NEW_LINE + "  Parameters: task number"
            + Ui.NEW_LINE + "  Example: " + COMMAND_WORD + " 1";

    public static void markTask(ArrayList<Task> tasks, String[] arrayOfInput) throws DukeException {
        int taskNumber = Integer.parseInt(Parser.parseCommand(arrayOfInput, COMMAND_WORD)) - 1;
        tasks.get(taskNumber).markAsDone();
        Ui.markOrUnmark(tasks, taskNumber);
    }
}
