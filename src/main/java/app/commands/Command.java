package app.commands;

import app.exceptions.DukeException;
import app.save.Storage;
import app.ui.Ui;
import app.tasks.TaskList;

/**
 * Represents the command class to be used by different user commands.
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Constructor for a new Command with initial exit condition as false.
     */
    public Command(){
        setIsExit(false);
    }

    /**
     * Getter to obtain the current exit status.
     * @return Returns the current exit condition.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Setter to set the current exit status.
     * @param exit The exit condition that is set to true to exit Duke, and false otherwise.
     */
    public void setIsExit(boolean exit) {
        this.isExit = exit;
    }

    /**
     * Method to execute any given command.
     * @param tasks Task-list containing the existing tasks.
     * @param ui User interface to print message.
     * @param storage Saving of tasks to memory.
     * @throws DukeException If error occurs during execution of any command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
