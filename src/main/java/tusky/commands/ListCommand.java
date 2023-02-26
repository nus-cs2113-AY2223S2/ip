package tusky.commands;

import tusky.storage.Storage;
import tusky.tasks.TaskList;
import tusky.ui.Ui;

public class ListCommand extends Command{
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        ui.showListTasks(tasks);
    }
}
