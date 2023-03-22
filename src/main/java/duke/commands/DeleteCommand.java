package duke.commands;

import duke.commands.task.Task;

import static duke.Ui.DISPLAYED_INDEX_OFFSET;

/**
 * Deletes a task from the task list.
 */

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_DELETE_SUCCESS = "Deleted Task: %1$s";
    public static final String MESSAGE_DELETE_FAIL = "Task number %1$d does not exist!";

    public DeleteCommand(int targetIndex) {
        super(targetIndex);
    }

    /**
     * Deletes a task if index given is valid
     * @return CommandResult object containing feedback message
     */

    public CommandResult execute() {
        int taskNumber = targetIndex + DISPLAYED_INDEX_OFFSET;
        try {
            Task target = taskList.getTask(targetIndex);
            taskList.deleteTask(targetIndex);
            return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, target.formattedTask));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(String.format(MESSAGE_DELETE_FAIL, taskNumber));
        }
    }
}
