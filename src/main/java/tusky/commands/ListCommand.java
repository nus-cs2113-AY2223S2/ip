package tusky.commands;

import tusky.storage.Storage;
import tusky.tasks.TaskList;
import tusky.ui.Ui;

/**
 * Command to list all the tasks in the TaskList
 */
public class ListCommand extends Command{
    public ListCommand() {
        super(false);
    }

    /**
     * Lists all the tasks in the TaskList
     * @param tasks The TaskList to be used for commands
     * @param ui The Ui class for interacting with the user
     * @param storage The Storage class for interacting with the file
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        ui.showListTasks(tasks);
    }
}
