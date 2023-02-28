package psyduck.command;

import psyduck.tasklist.TaskList;
import psyduck.ui.ErrorMessage;
import psyduck.ui.Ui;
import psyduck.task.Task;
public class MarkTaskCommand extends Command {

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
