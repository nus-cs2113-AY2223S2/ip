package tusky.commands;

import tusky.storage.Storage;
import tusky.tasks.TaskList;
import tusky.tasks.Task;
import tusky.ui.Ui;
public class AddCommand extends Command{
    Task task;
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAddTask(task, tasks.size());
        storage.writeFile(tasks);
    }
}
