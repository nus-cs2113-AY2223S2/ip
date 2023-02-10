package Command;

import Entities.TaskList;
import Exceptions.DukeException;
import FileUtils.Storage;
import Output.UI;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.printTasks(tasks);
    }
}
