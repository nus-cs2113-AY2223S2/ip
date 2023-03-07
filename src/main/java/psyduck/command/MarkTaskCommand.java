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
     * @param input the string input from the user.
     * @param tasks the list containing the task to be marked.
     * @param ui the user interface that interact with the user.
     */
    @Override
    public void executeCommand(String input, TaskList tasks, Ui ui) {
        try {
            String format = parser.prepareMark(input);
            int taskNum = Integer.parseInt(format);
            Task task = TaskList.getTask(taskNum);
            TaskList.getTask(taskNum).markDone();
            Ui.printTaskMarked(task, tasks);
        } catch (NullPointerException e) {
            ErrorMessage.printMarkTaskErrorMessage();
        }  catch (IndexOutOfBoundsException e) {
            ErrorMessage.printMarkTaskErrorMessage();
        }
    }
}
