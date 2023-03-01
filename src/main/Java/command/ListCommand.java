package command;

import jonathan.Storage;
import task.TaskList;
import jonathan.Ui;

public class ListCommand extends Command {

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showAllTasks(tasks);
        return tasks;
    }

}
