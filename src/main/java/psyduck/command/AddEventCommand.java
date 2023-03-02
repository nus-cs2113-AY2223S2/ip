package psyduck.command;

import psyduck.task.Event;
import psyduck.tasklist.TaskList;
import psyduck.ui.Ui;

public class AddEventCommand extends Command {

    /**
     * Executes the command to add an event task.
     *
     * @param input the string array containing the strings required to create
     *              an event task.
     * @param tasks the array list which the task is added to.
     * @param ui the user interface which interacts with the user.
     */
    @Override
    public void executeCommand(String[] input, TaskList tasks, Ui ui) {
        Event task = new Event(input[0], input[1], input[2]);
        tasks.addTask(task);
        Ui.printTaskAdded(tasks);
    }
}
