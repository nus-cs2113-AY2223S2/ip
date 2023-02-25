package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

/**
 * Represents an executable command from the user. A <code>EchoCommand</code> object contains a string to be echoed
 * back to the user.
 */
public class EchoCommand extends Command {
    private String fullCommand;

    public EchoCommand (String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * This method echoes <code>fullCommand</code> to the user.
     *
     * @param tasks The taskList containing all the tasks in Luke.
     * @param ui A Ui object which handles outputs to the user.
     * @param storage An object responsible for saving information in Luke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString(this.fullCommand);
    }
}
