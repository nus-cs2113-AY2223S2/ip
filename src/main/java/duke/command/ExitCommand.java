package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void run(TaskList taskList) {
        Ui.printExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
