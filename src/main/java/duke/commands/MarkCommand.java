package duke.commands;

import duke.commands.task.Task;

import static duke.Ui.DISPLAYED_INDEX_OFFSET;

/**
 * Changes the status of a task in the task list to completed.
 */

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_MARK_SUCCESS = "Marked Task: %1$s";
    public static final String MESSAGE_MARK_FAIL = "Task number %1$d does not exist!";

    public MarkCommand(int targetIndex) {
        super(targetIndex);
    }

    /**
     * Changes the status of a task in the task list specified by its task number to completed
     * @return CommandResult object containing feedback of execution
     */

    public CommandResult execute() {
        int taskNumber = targetIndex + DISPLAYED_INDEX_OFFSET;
        try {
            Task target = taskList.getTask(targetIndex);
            target.setDone();
            return new CommandResult(String.format(MESSAGE_MARK_SUCCESS, target.formattedTask));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(String.format(MESSAGE_MARK_FAIL, taskNumber));
        }
    }

}
