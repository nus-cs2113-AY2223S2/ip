package duke.command;

import duke.task.TaskList;

/**
 * Parent class for Command objects.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList The task list that the command is executed on.
     */
    public abstract void run(TaskList taskList);

    /**
     * Returns whether a command should terminate the program.
     *
     * @return True for the exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
