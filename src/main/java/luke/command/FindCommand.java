package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.task.Task;

import java.util.ArrayList;

/**
 * Represents an executable command from the user. A <code>FindCommand</code> object contains the keyword that the user
 * is looking to filter the tasks by. This object then prints out a list of tasks containing the keyword.
 */
public class FindCommand extends Command {
    private String toFind;
    private static final String FIRST_LINE = "Printing matching tasks...";

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    /**
     * This method prints out a list of tasks containing <code>toFind</code> in the task name.
     * If the taskList is empty, it informs the user that the taskList is empty.
     * If there are no tasks containing <code>toFind</code> in the name, it informs the user that there are no similar
     * tasks.
     *
     * @param tasks The taskList containing all the tasks in Luke.
     * @param ui A Ui object which handles outputs to the user.
     * @param storage An object responsible for saving information in Luke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printEmptyList();
            return;
        }
        ArrayList<Task> found = tasks.findTask(toFind);
        if (found.isEmpty()) {
            ui.printNoSimilarTasks();
            return;
        }
        ui.printTaskList(found, FIRST_LINE);
    }
}
