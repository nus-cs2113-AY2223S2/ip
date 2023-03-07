package psyduck.command;

import psyduck.exceptions.InvalidDeadlineFormatException;
import psyduck.exceptions.TaskEmptyException;
import psyduck.task.Deadline;
import psyduck.tasklist.TaskList;
import psyduck.ui.ErrorMessage;
import psyduck.ui.Ui;

/**
 * Represents the command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {

    /**
     * Executes the command to add a deadline task.
     *
     * @param input the string input from the user.
     * @param tasks the array list which the task is added to.
     * @param ui the user interface which interacts with the user.
     */

    @Override
    public void executeCommand(String input, TaskList tasks, Ui ui) {
        try {
            String[] format = parser.prepareDeadline(input);
            Deadline task = new Deadline(format[0], format[1]);
            tasks.addTask(task);
            Ui.printTaskAdded(tasks);
        } catch (TaskEmptyException e) {
            ErrorMessage.printTaskEmptyMessage();
        } catch (InvalidDeadlineFormatException e) {
            ErrorMessage.printInvalidDeadlineFormatMessage();
        }

    }
}
