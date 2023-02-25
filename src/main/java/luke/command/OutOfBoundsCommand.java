package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

/**
 * Represents an executable command. A <code>OutOfBoundsCommand</code> object is created whenever the user key in a
 * serial number that is not valid.
 */
public class OutOfBoundsCommand extends Command {
    /**
     * This method prints a string to inform the user they have keyed in an invalid index.
     *
     * @param tasks The taskList containing all the tasks in Luke.
     * @param ui A Ui object which handles outputs to the user.
     * @param storage An object responsible for saving information in Luke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printOutOfBounds();
    }
}
