package psyduck.command;

import psyduck.exceptions.TaskEmptyException;
import psyduck.tasklist.TaskList;
import psyduck.ui.ErrorMessage;
import psyduck.ui.Ui;
import psyduck.task.*;

/**
 * Represents the command to add a to-do task.
 */
public class AddToDoCommand extends Command {

    /**
     * Executes the command to add a to-do task.
     *
     * @param input the string input from the user.
     * @param tasks the array list which the task is added to.
     * @param ui the user interface which interacts with the user.
     */
    @Override
    public void executeCommand(String input, TaskList tasks, Ui ui) {
        try {
            String format = parser.prepareToDo(input);
            ToDo task = new ToDo(format);
            tasks.addTask(task);
            Ui.printTaskAdded(tasks);
        } catch (TaskEmptyException e) {
            ErrorMessage.printTaskEmptyMessage();
        }
    }
}
