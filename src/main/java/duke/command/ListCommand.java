package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;

public class ListCommand extends Command {
    @Override
    public void executor(TaskList tasks, UI ui) {
        String output = tasks.listAll();
        ui.print(output);
        ui.printLine();
    }
}
