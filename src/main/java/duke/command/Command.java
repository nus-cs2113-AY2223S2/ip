package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * Base class for all commands. Runs the required actions in the overridden method `executor()`. Performs the save
 * operation every time a command is executed.
 */
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
