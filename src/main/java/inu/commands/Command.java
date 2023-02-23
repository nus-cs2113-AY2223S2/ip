package inu.commands;

import inu.task.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Checks if exit command is inputted by user.
     *
     * @return true if exit command is input, false otherwise.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }

    /**
     * Executes the command and returns the corresponding feedback to user.
     *
     * @param taskList task list to contain the tasks.
     */
    public abstract CommandResult execute(TaskList taskList);

}
