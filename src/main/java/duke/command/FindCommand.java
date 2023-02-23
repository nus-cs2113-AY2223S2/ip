package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.findTasks(keyword));
    }
}
