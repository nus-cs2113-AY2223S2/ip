package tusky.commands;

import tusky.storage.Storage;
import tusky.tasks.TaskList;
import tusky.ui.Ui;
public class UnmarkCommand extends Command{
    private final int index;
    public UnmarkCommand (int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showUnmarkTask(tasks.unmarkTask(index));
        storage.writeFile(tasks);
    }
}
