package tusky.commands;

import tusky.storage.Storage;
import tusky.tasks.TaskList;
import tusky.ui.Ui;

public class FindCommand extends Command{
    private final String keyword;
    public FindCommand (String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFoundTasks(tasks.findTasks(keyword));
    }
}
