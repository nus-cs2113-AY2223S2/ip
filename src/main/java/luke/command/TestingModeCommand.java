package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

/**
 * Represents an executable command from the user. A <code>TestingModeCommand</code> is created to clear the contents
 * of the files used to store data in Luke. This ensures that Luke starts from a clean state whenever automated testing
 * is used.
 */
public class TestingModeCommand extends Command {
    /**
     * This method clears all data stored in the data files and reset the taskList to an empty state.
     *
     * @param tasks The taskList containing all the tasks in Luke.
     * @param ui A Ui object which handles outputs to the user.
     * @param storage An object responsible for saving information in Luke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.clearFiles();
        tasks.resetTaskList();
        ui.printTestingMode();
    }
}
