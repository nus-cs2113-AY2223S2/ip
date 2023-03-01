package command;

import jonathan.Storage;
import task.TaskList;
import jonathan.Ui;

public class TaskNotFoundCommand extends Command {
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskNotFound();
        return tasks;
    }
}
