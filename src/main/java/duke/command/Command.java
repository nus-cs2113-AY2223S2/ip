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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UnexpectedException {
    }

    /**
     * Sets <code>isExit</code> to <code>true</code>.
     * Called when the current Command is the ExitCommand.
     */
    public void setExit() {
        isExit = true;
    }

    public Boolean getExit() {
        return isExit;
    }

}
