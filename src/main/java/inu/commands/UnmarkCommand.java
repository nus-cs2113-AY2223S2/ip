package inu.commands;

import inu.commons.Messages;
import inu.storage.StorageFile;
import inu.task.TaskList;

/**
 * Marks a task in the task list as not done.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    private final int targetIndex;

    /**
     * Constructor.
     *
     * @param targetIndex index of the task to be marked.
     */
    public UnmarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public CommandResult execute(TaskList taskList) {
        taskList.unmarkTask(targetIndex);
        StorageFile.saveTaskListToFile(taskList);
        return new CommandResult(Messages.MESSAGE_UNMARK_TASK + "\n" + taskList.getTask(targetIndex).toString());
    }

}
