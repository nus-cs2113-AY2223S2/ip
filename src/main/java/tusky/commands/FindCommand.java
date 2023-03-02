package tusky.commands;

import tusky.storage.Storage;
import tusky.tasks.TaskList;
import tusky.ui.Ui;

/**
 * Command to find tasks that contain a keyword
 */
public class FindCommand extends Command{
    private final String keyword;
    public FindCommand (String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Finds tasks that contain the keyword and displays them to the user.
     * @param tasks The TaskList to be used for commands
     * @param ui The Ui class for interacting with the user
     * @param storage The Storage class for interacting with the file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFoundTasks(tasks.findTasks(keyword));
    }
}
