package Command;

import Entities.TaskList;
import Exceptions.DukeException;
import FileUtils.Storage;
import Output.UI;

/**
 * Command to list all current tasks
 */
public class ListCommand extends Command {
    /**
     * Executes printing of all current tasks
     * @param tasks Holds currently added tasks
     * @param ui Responsible for printing all currently added tasks
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.printTasks(tasks);
    }
}
