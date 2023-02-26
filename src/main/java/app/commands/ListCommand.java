package app.commands;

import app.exceptions.DukeException;
import app.save.Storage;
import app.tasks.TaskList;
import app.ui.Ui;

/**
 * Represents a command to print all tasks in current Task-list.
 */
public class ListCommand extends Command{

    /**
     * Prints tasks in correct format given current tasks.
     * @param tasks Task-list containing the existing tasks.
     * @param ui User interface to print lists.
     * @param storage Saving of tasks to memory.
     * @throws DukeException If error in printing task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printTasks(tasks);
    }
}
