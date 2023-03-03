package duke.commands;

import duke.commands.task.Task;

import static duke.Ui.DISPLAYED_INDEX_OFFSET;

/**
 * Changes the status of a task in the task list to uncompleted.
 */

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_UNMARK_SUCCESS = "Unmarked Task: %1$s";
    public static final String MESSAGE_UNMARK_FAIL = "Task number %1$d does not exist!";

    public UnmarkCommand(int targetIndex) {
        super(targetIndex);
    }

    /**
     * Changes the status of a task in the task list specified by its task number to uncompleted
     * @return CommandResult object containing feedback of execution
     */

    public CommandResult execute() {
        int taskNumber = targetIndex + DISPLAYED_INDEX_OFFSET;
        try {
            Task target = taskList.getTask(targetIndex);
            target.setUndone();
            return new CommandResult(String.format(MESSAGE_UNMARK_SUCCESS, target.formattedTask));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(String.format(MESSAGE_UNMARK_FAIL, taskNumber));
        }
    }

}