package tusky.commands;

import tusky.storage.Storage;
import tusky.tasks.TaskList;
import tusky.tasks.Task;
import tusky.ui.Ui;

/**
 * Command to add a task to the task list
 */
public class AddCommand extends Command{
    Task task;
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    /**
     * Adds a task to the task list and writes the updated task list to the storage file.
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAddTask(task, tasks.size());
        storage.writeFile(tasks);
    }
}
