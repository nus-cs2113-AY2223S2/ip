package duke.command;

import duke.TaskList;
import duke.UI;
import duke.Storage;


public class ListCommand extends Command {

    public ListCommand() {

    }

    public void execute(TaskList tasks, Storage storage) {
        UI.printAllTasks(tasks);
    }
}
