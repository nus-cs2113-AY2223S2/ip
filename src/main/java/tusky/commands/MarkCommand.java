package tusky.commands;

import tusky.storage.Storage;
import tusky.tasks.TaskList;
import tusky.ui.Ui;
public class MarkCommand extends Command{
    private final int index;
    public MarkCommand (int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMarkTask(tasks.markTask(index));
        storage.writeFile(tasks);
    }
}
