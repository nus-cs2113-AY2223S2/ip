package psyduck.command;

import psyduck.tasklist.TaskList;
import psyduck.ui.Ui;
import psyduck.task.*;

/**
 * Represents the command to add a to-do task.
 */
public class AddToDoCommand extends Command {

    /**
     * Executes the command to add a to-do task.
     *
     * @param input the string array containing the strings required to create
     *              a to-do task.
     * @param tasks the array list which the task is added to.
     * @param ui the user interface which interacts with the user.
     */
    @Override
    public void executeCommand(String[] input, TaskList tasks, Ui ui) {
        ToDo task = new ToDo(input[0]);
        tasks.addTask(task);
        Ui.printTaskAdded(tasks);
    }
}
