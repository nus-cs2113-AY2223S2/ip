package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        this.isExitCommand = true;
    }

    @Override
    public void executor(TaskList tasks, UI ui) {
        ui.printExit();
    }
}
