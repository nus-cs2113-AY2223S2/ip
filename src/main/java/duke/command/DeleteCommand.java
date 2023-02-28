package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {

    private int taskNumber;
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.getTask(taskNumber);
            UI.printDeleteTask();
            UI.printTask(task);
            tasks.removeTask(taskNumber);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("The given task number does not exist. ):");
        } catch (NumberFormatException e) {
            System.out.println("The task index must be numeric.");
        }
    }
}
