package duke.command;

import duke.exceptions.NoTaskException;
import duke.tasks.TaskList;
import duke.ui.UI;

public class ListCommand extends Command {
    @Override
    public void executor(TaskList tasks, UI ui) throws NoTaskException {
        String output = tasks.listAll();
        ui.print(output);
        ui.printLine();
    }
}
