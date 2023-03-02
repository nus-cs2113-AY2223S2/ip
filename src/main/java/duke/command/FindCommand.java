package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.UI;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) {
        ui.printFoundTasks(tasks.findTasks(keyword));
    }
}
