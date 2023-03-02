package duke.commands;

import duke.commands.task.Task;

import static duke.Ui.DISPLAYED_INDEX_OFFSET;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_DELETE_SUCCESS = "Deleted Task: %1$s";
    public static final String MESSAGE_DELETE_FAIL = "Task number %1$d does not exist!";

    public DeleteCommand(int targetIndex) {
        super(targetIndex);
    }

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
