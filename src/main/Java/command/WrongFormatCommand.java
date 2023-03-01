package command;

import jonathan.Storage;
import task.TaskList;
import jonathan.Ui;

public class WrongFormatCommand extends Command {
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showWrongFormat();
        return tasks;
    }
}
