package Command;

import Entities.TaskList;
import Exceptions.DukeException;
import FileUtils.Storage;
import Output.UI;

/**
 * Command to exit program
 */
public class ExitCommand extends Command {

    /**
     * Executes exit command
     * @param tasks Holds all currently added tasks
     * @param ui Responsible for printing goodbye message
     * @param storage Handles the storage of tasks into database
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.printGoodbye();
        storage.write(tasks);
        setIsExit(true);
    }
}
