package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

import java.rmi.UnexpectedException;

/**
 * Abstract class Command is the parent of all types of Commands
 */
public abstract class Command {

    private Boolean isExit = false;

    /**
     * Executes the command
     *
     * @param tasks TaskList containing all currently saved tasks
     * @param ui To print output messages to user
     * @param storage To update the local save file if the TaskList is modified
     * @throws UnexpectedException If something unexpected occurs
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UnexpectedException {
    }

    /**
     * Sets {@code isExit} to {@code true}.
     * Called when the current Command is the ExitCommand.
     */
    public void setExit() {
        isExit = true;
    }

    /**
     * Gets the exit status of the program.
     *
     * @return {@code true} if exit command has been issued, {@code false} otherwise
     */
    public Boolean getExit() {
        return isExit;
    }

}
