package Command;

import Entities.TaskList;
import Exceptions.DukeException;
import FileUtils.Storage;
import Output.UI;

public class ExitCommand extends Command {


    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.printGoodbye();
        storage.write(tasks);
        setIsExit(true);
    }
}
