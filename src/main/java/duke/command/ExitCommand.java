package duke.command;

import duke.tasklist.TaskList;
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
        // exit message will be handled by shutdown hook
    }
}
