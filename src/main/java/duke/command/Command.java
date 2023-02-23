package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

public class Command {
    public boolean isExitCommand = false;

    public void executor(TaskList tasks, UI ui) throws Exception {
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws Exception {
        executor(tasks, ui);
        storage.save(tasks.toJson());
    }

    public boolean isExit() {
        return isExitCommand;
    }
}
