package psyduck.command;

import psyduck.task.Task;
import psyduck.tasklist.TaskList;
import psyduck.ui.ErrorMessage;
import psyduck.ui.Ui;

/**
 * Represents the command to unmark a task.
 */
public class UnmarkTaskCommand extends Command{

    /**
     * Executes the command to unmark a specific task in the list.
     *
     * @param input the string array containing the target position
     *              of the task to be unmarked.
     * @param tasks the list containing the task to be unmarked.
     * @param ui the user interface that interact with the user.
     */
    @Override
    public void executeCommand(String[] input, TaskList tasks, Ui ui) {
        try {
            int taskNum = Integer.parseInt(input[0]);
            Task task = TaskList.getTask(taskNum);
            TaskList.getTask(taskNum).unmarkDone();
            Ui.printTaskMarked(task, tasks);
        } catch (NullPointerException e) {
            ErrorMessage.printUnmarkTaskErrorMessage();
        } catch (NumberFormatException e) {
            ErrorMessage.printUnmarkTaskErrorMessage();
        } catch (IndexOutOfBoundsException e) {
            ErrorMessage.printUnmarkTaskErrorMessage();
        }
    }

}
