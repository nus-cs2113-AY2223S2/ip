package command;

import jonathan.Storage;
import task.Task;
import task.TaskList;
import jonathan.Ui;

public class UnmarkCommand extends Command {
    private final int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(taskNum - 1);
        task.setDone(false);
        ui.showUnmarked(task);
        return tasks;
    }
}
