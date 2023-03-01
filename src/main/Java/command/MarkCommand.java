package command;

import jonathan.Storage;
import task.Task;
import task.TaskList;
import jonathan.Ui;

public class MarkCommand extends Command {
    private final int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(taskNum - 1);
        task.setDone(true);
        ui.showMarked(task);
        return tasks;
    }
}
