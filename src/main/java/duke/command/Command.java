package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Base class for all commands. Runs the required actions in the overridden method `executor()`. Performs the save
 * operation every time a command is executed.
 */
public class Command {
    public boolean isExitCommand = false;

    public void execute(TaskList tasks, UI ui, Storage storage) throws Exception {
        executor(tasks, ui);
        storage.save(tasks.toJson());
    }

    public void executor(TaskList tasks, UI ui) throws Exception {
    }

    public boolean isExit() {
        return isExitCommand;
    }
}
