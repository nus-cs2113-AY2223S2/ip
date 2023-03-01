package duke.commands;

import duke.tasks.TaskList;

/**
 * Parent class for other Command objects.
 */
public abstract class Command {
    /**
     * Executes the command
     *
     * @param taskList The task list that the command is executed on.
     */
    public abstract void execute(TaskList taskList);

    /**
     * Returns a boolean to determine whether the program should terminate.
     *
     * @return False at the start as program just started running.
     */
    public boolean isExit() {
        return false;
    }
}
