package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.StringManipulation;

/**
 * Represents an executable command from the user. A <code>Command</code> object contains the details needed to execute
 * the command. Each subclass of <code>Command</code> will have an overridden method <code>execute</code> which has its
 * own implementation.
 */
public abstract class Command implements StringManipulation {
    /**
     * This method is to be overridden by each subclass for different implementations.
     *
     * @param tasks The taskList containing all the tasks in Luke.
     * @param ui A Ui object which handles outputs to the user.
     * @param storage An object responsible for saving information in Luke.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
