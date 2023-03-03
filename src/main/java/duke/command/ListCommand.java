package duke.command;

import duke.TaskList;
import duke.UI;
import duke.Storage;

/**
 * Command class for listing tasks.
 */
public class ListCommand extends Command {
    public ListCommand() {

    }

    /**
     * Prints out all tasks in the task list.
     *
     * @param tasks   The task list to be printed out.
     * @param storage Updates when task list is modified
     * @param ui      Prints entire task list.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) {
        ui.printAllTasks(tasks);
        ui.printSeparator();
    }
}
