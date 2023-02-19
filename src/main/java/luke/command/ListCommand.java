package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

/**
 * Represents an executable command from the user. A <code>ListCommand</code> object is created whenever a user wants
 * to list the task stored in the taskList. This object prints the taskList when the execute method is called.
 */
public class ListCommand extends Command {
    private static final String FIRST_LINE = "Printing Tasks...";

    /**
     * This method prints the tasks stored in the taskList in the order they are added by the user.
     * If the taskList is empty, this method informs the user that the taskList is empty.
     *
     * @param tasks The taskList containing all the tasks in Luke.
     * @param ui A Ui object which handles outputs to the user.
     * @param storage An object responsible for saving information in Luke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //Check if the TaskList is empty
        if (tasks.isEmpty()) {
            ui.printEmptyList();
            return;
        }
        ui.printTaskList(tasks.getTaskList(), FIRST_LINE);
    }
}
