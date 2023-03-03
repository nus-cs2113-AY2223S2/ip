package buddy.commands;

import buddy.exceptions.InvalidCommandException;
import buddy.tasks.TaskList;

/**
 * Constructor for Command class
 */
public abstract class Command {
    /**
     * Executes the commands by the user for all the different types of commands - actionCommands and addTaskCommands
     *
     * @param taskList List of tasks
     * @param input    Command inputted by the user
     */
    public abstract void executeCommand(TaskList taskList, String input) throws InvalidCommandException;
}
