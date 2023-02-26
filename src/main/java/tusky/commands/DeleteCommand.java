package tusky.commands;

import tusky.storage.Storage;
import tusky.tasks.TaskList;
import tusky.tasks.Task;
import tusky.ui.Ui;
public class DeleteCommand extends Command{
    private final int index;
    public DeleteCommand (int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTask(index);

        ui.showDeleteTask(task, tasks.size());
        storage.writeFile(tasks);
    }
}
