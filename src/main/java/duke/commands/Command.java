package duke.commands;


import duke.TaskList;
import duke.data.Task;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected TaskList taskList;

    /**
     * get the task of a specific position
     * @param index the position of the task
     * @return the task at the input index
     * @throws IndexOutOfBoundsException
     */
    protected Task getTargetTask(int index) throws IndexOutOfBoundsException{
        return taskList.getTask(index);
    }

    /**
     * Execute the command in different ways
     */
    abstract void execute();

}
