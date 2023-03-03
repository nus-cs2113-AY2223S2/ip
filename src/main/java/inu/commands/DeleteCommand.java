package inu.commands;

import inu.commons.Messages;
import inu.storage.StorageFile;
import inu.task.TaskList;

/**
 * Delete a task in the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final int targetIndex;

    /**
     * Constructor.
     *
     * @param targetIndex index of task to be deleted.
     */
    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        String deletedTask = taskList.getTask(targetIndex).toString();
        taskList.deleteTask(targetIndex);
        StorageFile.saveTaskListToFile(taskList);
        return new CommandResult(Messages.MESSAGE_DELETE_TASK + "\n" + deletedTask);
    }
}
