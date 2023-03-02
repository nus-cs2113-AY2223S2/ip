package tusky.commands;

import tusky.storage.Storage;
import tusky.tasks.TaskList;
import tusky.ui.Ui;

/**
 * Command to mark a task as not done
 */
public class UnmarkCommand extends Command{
    private final int index;
    public UnmarkCommand (int index) {
        super(false);
        this.index = index;
    }

    /**
     * Marks a task as done and writes the updated TaskList to the file
     * @param tasks The TaskList to be used for commands
     * @param ui The Ui class for interacting with the user
     * @param storage The Storage class for interacting with the file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showUnmarkTask(tasks.unmarkTask(index));
        storage.writeFile(tasks);
    }
}
