package psyduck.command;

import psyduck.task.Deadline;
import psyduck.tasklist.TaskList;
import psyduck.ui.Ui;

/**
 * Represents the command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {

    /**
     * Executes the command to add a deadline task.
     *
     * @param input the string array containing the strings required to create
     *              a deadline task.
     * @param tasks the array list which the task is added to.
     * @param ui the user interface which interacts with the user.
     */

    @Override
    public void executeCommand(String[] input, TaskList tasks, Ui ui) {
        Deadline task = new Deadline(input[0], input[1]);
        tasks.addTask(task);
        Ui.printTaskAdded(tasks);
    }
}
