package duke.commands;

import duke.commands.task.Task;

import static duke.Ui.DISPLAYED_INDEX_OFFSET;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_MARK_SUCCESS = "Marked Task: %1$s";
    public static final String MESSAGE_MARK_FAIL = "Task number %1$d does not exist!";

    public MarkCommand(int targetIndex) {
        super(targetIndex);
    }

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
