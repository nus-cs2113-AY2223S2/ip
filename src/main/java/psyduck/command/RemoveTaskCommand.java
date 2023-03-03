package psyduck.command;

import psyduck.tasklist.TaskList;
import psyduck.ui.ErrorMessage;
import psyduck.ui.Ui;
import psyduck.task.*;

/**
 * Represents the command to remove a task.
 */
public class RemoveTaskCommand extends Command{

    /**
     * Executes the command to remove a specific task from the list.
     *
     * @param input the string input from the user.
     * @param tasks the array list which the task is being removed.
     * @param ui the user interface which interacts with the user.
     */
    @Override
    public void executeCommand(String input, TaskList tasks, Ui ui) {
        try {
            String format = parser.prepareRemove(input);
            int taskNum = Integer.parseInt(format);
            Task temp = TaskList.getTask(taskNum);
            tasks.removeTask(taskNum);
            Ui.printTaskRemoved(temp, tasks);
        } catch (NullPointerException e) {
            ErrorMessage.printRemoveTaskErrorMessage();
        }  catch (IndexOutOfBoundsException e) {
            ErrorMessage.printRemoveTaskErrorMessage();
        }
    }
}
