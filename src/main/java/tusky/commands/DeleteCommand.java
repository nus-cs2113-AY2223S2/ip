package tusky.commands;

import tusky.storage.Storage;
import tusky.tasks.TaskList;
import tusky.tasks.Task;
import tusky.ui.Ui;

/**
 * Command to add a task to the task list
 */
public class DeleteCommand extends Command{
    private final int index;
    public DeleteCommand (int index) {
        super(false);
        this.index = index;
    }

    /**
     * Deletes a task from the TaskList and writes the updated TaskList to the file
     * @param tasks The TaskList to be used for commands
     * @param ui The Ui class for interacting with the user
     * @param storage The Storage class for interacting with the file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTask(index);
        ui.showDeleteTask(task, tasks.size());
        storage.writeFile(tasks);
    }
}
