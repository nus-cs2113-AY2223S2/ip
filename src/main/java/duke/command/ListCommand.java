package duke.command;

import duke.ui.Ui;

/**
 * Shows all tasks and their information in the task list
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String EMPTY_MESSAGE = " The list is empty!";

    public static final String MESSAGE = " Here are the tasks in your lists:";

    public static final String MESSAGE_USAGE = " list" + ": view all tasks in the task list. "
            + Ui.NEW_LINE + "  Example: list";

    /**
     * Executes the command and returns the result
     *
     * @return CommandResult with the relevant output message as its parameter
     */
    @Override
    public CommandResult execute() {
        String output = Ui.SEGMENT_LINE;
        int taskCount = taskList.getTaskCount();
        boolean isTaskCountZero = (taskCount == 0);
        if (isTaskCountZero) {
            output = String.join(Ui.NEW_LINE, output, ListCommand.EMPTY_MESSAGE);
        } else {
            output = String.join(Ui.NEW_LINE, output, ListCommand.MESSAGE);
            for (int i = 0; i < taskCount; i += 1) {
                output = String.join(Ui.NEW_LINE, output, (" " + (i + 1) + "." + taskList.getTaskFullDetails(i)));
            }
        }
        return new CommandResult(output);
    }
}
