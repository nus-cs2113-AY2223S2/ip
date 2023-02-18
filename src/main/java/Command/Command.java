package Command;

import Entities.TaskList;
import Exceptions.DukeException;
import FileUtils.Storage;
import Output.UI;

/**
 * Command is an abstract class that other commands are derived from
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Constructor for Command Class
     * Sets isExit to false
     */
    public Command() {
        setIsExit(false);
    }

    /**
     * Gets the boolean value isExit
     * @return whether to exit Duke
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Sets the boolean value isExit
     * @param isExit new value for isExit
     */
    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Abstract Method to be implemented by the child classes
     * @param tasks Handles manipulation of task list
     * @param ui Responsible for printing status of Duke
     * @param storage Handles the storage of tasks into database
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;
}
