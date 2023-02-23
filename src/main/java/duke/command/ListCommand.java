package duke.command;

import duke.exceptions.NoTaskException;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Lists all the tasks available.
 */
public class ListCommand extends Command {
    @Override
    public void executor(TaskList tasks, UI ui) throws NoTaskException {
        String output = tasks.listAll();
        ui.print(output);
        ui.printLine();
    }
}
