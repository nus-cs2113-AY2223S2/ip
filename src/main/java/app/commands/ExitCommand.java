package app.commands;

import app.exceptions.DukeException;
import app.save.Storage;
import app.tasks.Task;
import app.tasks.TaskList;
import app.ui.Ui;

/**
 * Represents a command to exit and stop running Duke.
 */
public class ExitCommand extends Command{

    /**
     * Sets exit condition to true to stop running Duke.
     * @param tasks Task-list containing the existing tasks.
     * @param ui User interface to print exit message.
     * @param storage Saving of tasks to memory.
     * @throws DukeException If error in exiting Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showExit();
        storage.write(tasks);
        setIsExit(true);
    }
}
