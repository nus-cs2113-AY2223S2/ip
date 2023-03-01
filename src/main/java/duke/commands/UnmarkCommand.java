package duke.commands;

import duke.commands.task.Task;

import static duke.Ui.DISPLAYED_INDEX_OFFSET;

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_UNMARK_SUCCESS = "Unmarked Task: %1$s";
    public static final String MESSAGE_UNMARK_FAIL = "Task number %1$d does not exist!";

    public UnmarkCommand(int targetIndex) {
        super(targetIndex);
    }

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