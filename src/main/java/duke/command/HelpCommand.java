package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;

public class HelpCommand extends Command {
    public HelpCommand() {
    }

    @Override
    public void executor(TaskList tasks, UI ui) {
        ui.printHelp();
    }
}
