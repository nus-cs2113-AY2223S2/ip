package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;

public class UnmarkTaskCommand extends Command {

    private int taskNumber;

    public UnmarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, Storage storage) {

        try {
            Task task = tasks.getTask(taskNumber);
            task.unmarkAsDone();
            UI.printMarkNotDone();
            UI.printTask(task);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("The given task number does not exist. ):");
        } catch (NumberFormatException e) {
            System.out.println("The task index must be numeric.");
        } catch (DukeException e) {
            System.out.println("The task is already marked as not done.");
        }

        try {
            storage.saveData(tasks);
        } catch (IOException e) {
            System.out.println("Unable to save.");
        }


    }
}
