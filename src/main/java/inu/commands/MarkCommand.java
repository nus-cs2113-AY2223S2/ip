package inu.commands;

import inu.commons.Messages;
import inu.task.TaskList;

/**
 * Marks a task in the task list as done.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    private final int targetIndex;

    /**
     * Constructor.
     *
     * @param targetIndex index of the task to be marked.
     */
    public MarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public CommandResult execute(TaskList taskList) {
        taskList.markTask(targetIndex);
        return new CommandResult(Messages.MESSAGE_MARK_TASK + "\n" + taskList.getTask(targetIndex).toString());
    }

}
