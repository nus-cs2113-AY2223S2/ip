package command;

import jonathan.Storage;
import task.TaskList;
import jonathan.Ui;

public class ByeCommand extends Command {

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.showGoodbye();
        return tasks;
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
