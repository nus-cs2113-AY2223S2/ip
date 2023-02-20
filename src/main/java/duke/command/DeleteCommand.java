package duke.command;

import duke.ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String OUTPUT_MESSAGE = " Noted. Task removed: ";
    public static final String REMAINING_TASK_MESSAGE = " You now have %d task[s] in the list.";
    public static final String INVALID_COMMAND_MESSAGE = " Invalid input! Valid input format: \"delete <number>\"";
    public static final String MESSAGE_USAGE = " " + COMMAND_WORD + ": deletes a task from the task list. "
            + Ui.NEW_LINE + "  Parameters: task number"
            + Ui.NEW_LINE + "  Example: " + COMMAND_WORD + " 1";

    public int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public CommandResult execute() {
        String output = Ui.SEGMENT_LINE;
        try {
            output = String.join(Ui.NEW_LINE, output, OUTPUT_MESSAGE,
                    ("   " + taskList.getTaskFullDetails(taskNumber)), DeleteCommand.REMAINING_TASK_MESSAGE);
            int taskCount = taskList.getTaskCount();
            output = output.replace("%d", Integer.toString(taskCount - 1));
            taskList.deleteTask(taskNumber);
            return new CommandResult(output);
        } catch (IndexOutOfBoundsException e) {
            output = Ui.craftExceedMessage(taskList.getTaskCount());
            return new CommandResult(output);
        }
    }
}
