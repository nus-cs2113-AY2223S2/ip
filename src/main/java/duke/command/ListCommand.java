package duke.command;

import duke.data.TaskList;
import duke.ui.*;

/**
 * Command to execute to list all current tasks in task list
 */
public class ListCommand extends Command {
    /**
     * list all the current task in task list
     *
     * @param input data of given command and description
     * @param tasks task list containing all current tasks
     * @param ui    UI object to print all tasks in task list
     */
    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        ui.printAllTasks(tasks);
    }
}
