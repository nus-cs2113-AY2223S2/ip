package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

/**
 * Represents an executable command. A <code>InvalidCommand</code> object is used whenever the user enter an invalid
 * command and Luke is unable to generate the appropriate <code>Command</code> object.
 */
public class InvalidCommand extends Command{
    /**
     * This method informs the user that they have keyed in an invalid command.
     *
     * @param tasks The taskList containing all the tasks in Luke.
     * @param ui A Ui object which handles outputs to the user.
     * @param storage An object responsible for saving information in Luke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printInvalidCommand();
    }
}
