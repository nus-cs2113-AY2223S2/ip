package duke.command;

import duke.task.DateTime;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

/** Finds tasks in task list that occurs on a given date */
public class DateCommand extends Command {

    public static final String COMMAND_WORD = "date";

    public static final String OUTPUT_MESSAGE = " The following task(s) occurs on %s:";

    public static final String NO_MENTIONED_TASK_DATE = " There are no tasks found with the date provided";

    public static final String INVALID_COMMAND_MESSAGE = " Invalid input! Valid input format: \"date <yyyy/mm/dd>\"";

    public static final String MESSAGE_USAGE = " " + COMMAND_WORD + ": shows tasks due on a given date. "
            + Ui.NEW_LINE + "  Parameters: date"
            + Ui.NEW_LINE + "  Example: " + COMMAND_WORD + " 2023/02/20";

    public LocalDate date;

    public DateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the command and returns the result
     *
     * @return CommandResult with the relevant output message as its parameter
     */
    public CommandResult execute() {
        String output = Ui.SEGMENT_LINE;
        TaskList dueTasks = taskList.getDueTasks(date);
        if (taskList.getTaskCount() == 0) {
            output = String.join(Ui.NEW_LINE, output, NO_MENTIONED_TASK_DATE);
        } else {
            output = String.join(Ui.NEW_LINE, output, OUTPUT_MESSAGE);
            output = output.replace("%s", date.format(DateTime.outDateFormatter));
            output = getFilteredTasksInformation(output, dueTasks);
        }
        return new CommandResult(output);
    }
}
