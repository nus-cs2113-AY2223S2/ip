package command;

import jonathan.Storage;
import task.Task;
import task.TaskList;
import jonathan.Ui;

public class DeleteCommand extends Command {
    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        ui.showDelete(tasks, task);
        return tasks;
    }
}
