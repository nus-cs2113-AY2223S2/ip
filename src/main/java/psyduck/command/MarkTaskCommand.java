package psyduck.command;

import psyduck.tasklist.TaskList;
import psyduck.ui.ErrorMessage;
import psyduck.ui.Ui;
import psyduck.task.Task;

/**
 * Represents the command to mark a task.
 */
public class MarkTaskCommand extends Command {

    /**
     * Executes the command to mark a specific task in the list.
     *
     * @param input the string array containing the target position
     *              of the task to be marked.
     * @param tasks the list containing the task to be marked.
     * @param ui the user interface that interact with the user.
     */
    @Override
    public void executeCommand(String[] input, TaskList tasks, Ui ui) {
        try {
            int taskNum = Integer.parseInt(input[0]);
            Task task = TaskList.getTask(taskNum);
            TaskList.getTask(taskNum).markDone();
            Ui.printTaskMarked(task, tasks);
        } catch (NullPointerException e) {
            ErrorMessage.printMarkTaskErrorMessage();
        } catch (NumberFormatException e) {
            ErrorMessage.printMarkTaskErrorMessage();
        } catch (IndexOutOfBoundsException e) {
            ErrorMessage.printMarkTaskErrorMessage();
        }
    }
}
