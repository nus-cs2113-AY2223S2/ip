package Command;

import Entities.TaskList;
import Exceptions.DukeException;
import FileUtils.Storage;
import Output.UI;

/**
 * Command to show tasks with upcoming deadlines
 */
public class UpcomingCommand extends Command {
    /**
     * Prints all upcoming tasks
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        TaskList upcomingTasks = tasks.getUpcomingTasks();
        String header = "Please be reminded that these tasks are due soon:";
        ui.printTasks(upcomingTasks, header);
    }
}
