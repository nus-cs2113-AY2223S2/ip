package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class ListCommand implements Command {
    public static final String COMMAND_WORD = "list";

    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
    }

    public boolean isExit() {
        return false;
    }
}
