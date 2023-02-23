package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;

public class ExitCommand extends Command {
    public ExitCommand() {
        this.isExitCommand = true;
    }

    @Override
    public void executor(TaskList tasks, UI ui) {
        ui.printExit();
    }
}
