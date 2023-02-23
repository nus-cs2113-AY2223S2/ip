package inu.commands;

import inu.commons.Messages;
import inu.task.TaskList;

/**
 * Marks a task in the task list as not done.
 */
public class UnMarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    private final int targetIndex;

    /**
     * Constructor.
     *
     * @param targetIndex index of the task to be marked.
     */
    public UnMarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public CommandResult execute(TaskList taskList) {
        taskList.unMarkTask(targetIndex);
        return new CommandResult(Messages.MESSAGE_UNMARK_TASK + "\n" + taskList.getTask(targetIndex).toString());
    }

}
