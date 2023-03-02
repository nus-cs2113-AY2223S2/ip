package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Task;

import java.io.IOException;

public class MarkTaskCommand extends Command {
    private int taskNumber;

    public MarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) {
        try {
            Task task = tasks.getTask(taskNumber);
            task.markAsDone();
            ui.printMarkDone();
            ui.printTask(task);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskNumber();
        } catch (DukeException e) {
            ui.printTaskAlreadyMarked();
        }

        try {
            storage.saveData(tasks, ui);
        } catch (IOException e) {
            ui.printSavingError();
        }
    }
}
