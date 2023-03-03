package psyduck.command;

import psyduck.exceptions.InvalidEventFormatException;
import psyduck.exceptions.TaskEmptyException;
import psyduck.task.Event;
import psyduck.tasklist.TaskList;
import psyduck.ui.ErrorMessage;
import psyduck.ui.Ui;

public class AddEventCommand extends Command {

    /**
     * Executes the command to add an event task.
     *
     * @param input the string input from the user.
     * @param tasks the array list which the task is added to.
     * @param ui the user interface which interacts with the user.
     */
    @Override
    public void executeCommand(String input, TaskList tasks, Ui ui) {
        try {
            String [] format = parser.prepareEvent(input);
            Event task = new Event(format[0], format[1], format[2]);
            tasks.addTask(task);
            Ui.printTaskAdded(tasks);
        } catch (TaskEmptyException e) {
            ErrorMessage.printTaskEmptyMessage();
        } catch (InvalidEventFormatException e) {
            ErrorMessage.printInvalidEventFormatMessage();
        }

    }
}
