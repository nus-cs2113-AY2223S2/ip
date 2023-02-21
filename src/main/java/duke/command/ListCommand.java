package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The ListCommand class that inherits from the Command class which handles the user wanting to list out all the tasks
 * in the TaskList
 */
public class ListCommand extends Command {
    /**
     * Executes the ListCommand where it lists out all the tasks currently in the TaskList and these tasks are indexed
     *
     * @param tasks    the TaskList that is being referred to
     * @param database the Storage that is being referred to
     */
    @Override
    public void execute(TaskList tasks, Storage database) {
        Ui.printList(tasks);
    }
}
