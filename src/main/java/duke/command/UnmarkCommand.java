package duke.command;

import duke.ui.Ui;

/**
 * Unmarks a task as referenced by a task number
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE = " OK, I've marked this task as not done yet:";

    public static final String INVALID_COMMAND_MESSAGE = " Invalid input! Valid input format: \"unmark <number>\"";

    public static final String MESSAGE_USAGE = " " + COMMAND_WORD + ": marks a task from the task list as not done. "
            + Ui.NEW_LINE + "  Parameters: task number"
            + Ui.NEW_LINE + "  Example: " + COMMAND_WORD + " 1";

    public int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command and returns the result
     *
     * @return CommandResult with the relevant output message as its parameter
     */
    @Override
    public CommandResult execute() {
        String output = Ui.SEGMENT_LINE;
        try {
            taskList.markTaskNotDone(taskNumber);
            output = String.join(Ui.NEW_LINE, output, MESSAGE,
                    ("   " + taskList.getTaskFullDetails(taskNumber)));
            return new CommandResult(output);
        } catch (IndexOutOfBoundsException e) {
            output = Ui.craftExceedMessage(taskList.getTaskCount());
            return new CommandResult(output);
        }
    }
}
