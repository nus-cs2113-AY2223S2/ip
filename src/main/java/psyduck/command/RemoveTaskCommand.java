package psyduck.command;

import psyduck.tasklist.TaskList;
import psyduck.ui.ErrorMessage;
import psyduck.ui.Ui;
import psyduck.task.*;

public class RemoveTaskCommand extends Command{

    @Override
    public void executeCommand(String[] input, TaskList tasks, Ui ui) {
        try {
            int taskNum = Integer.parseInt(input[0]);
            Task temp = TaskList.getTask(taskNum);
            tasks.removeTask(taskNum);
            Ui.printTaskRemoved(temp, tasks);
        } catch (NullPointerException e) {
            ErrorMessage.printRemoveTaskErrorMessage();
        } catch (NumberFormatException e) {
            ErrorMessage.printRemoveTaskErrorMessage();
        } catch (IndexOutOfBoundsException e) {
            ErrorMessage.printRemoveTaskErrorMessage();
        }
    }
}
