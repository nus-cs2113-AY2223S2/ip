package app.commands;

import app.exceptions.DukeException;
import app.save.Storage;
import app.tasks.Task;
import app.tasks.TaskList;
import app.ui.Ui;

public class ExitCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showExit();
        storage.write(tasks);
        setIsExit(true);
    }
}
