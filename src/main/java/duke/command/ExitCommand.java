package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

/**
 * A <code>ExitCommand</code> object takes care of the exit sequence of Duke.
 */
public class ExitCommand extends Command {

    @Override
    public void run(TaskList taskList) {
        Ui.printExiting();
    }

    @Override
    public boolean toExit() {
        return true;
    }
}
