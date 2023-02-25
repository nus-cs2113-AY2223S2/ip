package app.commands;

import app.exceptions.DukeException;
import app.save.Storage;
import app.tasks.TaskList;
import app.ui.Ui;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printTasks(tasks);
    }
}
