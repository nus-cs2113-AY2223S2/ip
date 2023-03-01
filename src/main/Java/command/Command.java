package command;

import jonathan.JonathanException;
import jonathan.Storage;
import task.TaskList;
import jonathan.Ui;

/**
 * Abstract class to implement command class.
 */
public abstract class Command {

    /**
     * Method to execute the the command.
     * @param tasks list of tasks.
     * @param ui interface of the program.
     * @param storage storage of the program.
     * @return tasks.
     * @throws JonathanException to handle exception.
     */
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws JonathanException {
        return tasks;
    }

    /**
     * Method to indicate to exit the program after executed
     * @return boolean.
     */
    public boolean isExit() {
        return false;
    }
}
