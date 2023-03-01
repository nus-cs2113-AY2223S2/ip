package Alex.command;

import Alex.task.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result. Subclass will override this method
     *
     * @param taskList the taskList which contains all different tasks
     * @return CommandResult as output to user
     */
    public abstract CommandResult execute(TaskList taskList);
}
