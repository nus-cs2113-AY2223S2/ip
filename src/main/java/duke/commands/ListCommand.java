package duke.commands;

import duke.file.TaskList;
import duke.ui.UI;

/**
 * Command to output all stored tasks in the tasklist
 */
public class ListCommand extends Command {
    /**
     * outputs tasklist and its stored tasks
     *
     * @param input details of the user command
     * @param tasks tasklist which contains all the tasks
     * @param ui    UI to output all stored tasks within the tasklist
     */
    @Override
    public void execute(String input, TaskList tasks, UI ui) {
        ui.printTasksArray(tasks);
    }
}
