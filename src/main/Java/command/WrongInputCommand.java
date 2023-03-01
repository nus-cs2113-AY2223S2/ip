package command;

import jonathan.Storage;
import task.TaskList;
import jonathan.Ui;

public class WrongInputCommand extends Command {
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showWrongInput();
        return tasks;
    }
}
