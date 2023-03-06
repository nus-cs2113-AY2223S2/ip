package duke.command;

import duke.ui.Ui;

/**
 * Marks a task as referenced by a task number
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE = " Awesome! I've marked this task as done:";

    public static final String INVALID_COMMAND_MESSAGE = " Invalid input! Valid input format: \"mark <number>\"";

    public static final String MESSAGE_USAGE = " " + COMMAND_WORD + ": marks a task from the task list as done. "
            + Ui.NEW_LINE + "  Parameters: task number"
            + Ui.NEW_LINE + "  Example: " + COMMAND_WORD + " 1";

    public int taskNumber;

    public MarkCommand(int taskNumber) { // todo error handling
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
            taskList.markTaskDone(taskNumber);
            output = String.join(Ui.NEW_LINE, output, MESSAGE,
                    ("   " + taskList.getTaskFullDetails(taskNumber)));
            return new CommandResult(output);
        } catch (IndexOutOfBoundsException e) {
            output = Ui.craftExceedMessage(taskList.getTaskCount());
            return new CommandResult(output);
        }
    }
}
