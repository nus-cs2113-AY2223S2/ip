package psyduck.command;

import psyduck.tasklist.TaskList;
import psyduck.ui.Ui;

/**
 * Represents the command to list out all the tasks currently in the list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list out the tasks.
     *
     * @param input not used here.
     *
     * @param tasks the array list which contains the tasks that will be listed out.
     * @param ui not used here.
     */
    @Override
    public void executeCommand(String[] input, TaskList tasks, Ui ui) {
        tasks.listTasks();
    }
}
