package duke.command;

import duke.ui.Ui;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE = " OK, I've marked this task as not done yet:";
    public static final String MESSAGE_USAGE = " " + COMMAND_WORD + ": marks a task from the task list as not done. "
            + Ui.NEW_LINE + "  Parameters: task number"
            + Ui.NEW_LINE + "  Example: " + COMMAND_WORD + " 1";

    public int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

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
