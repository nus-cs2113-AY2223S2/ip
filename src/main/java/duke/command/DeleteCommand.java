package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) {
        try {
            Task task = tasks.getTask(taskNumber);
            ui.printDeleteTask();
            ui.printTask(task);
            tasks.removeTask(taskNumber);
            storage.saveData(tasks, ui);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskNumber();
        } catch (IOException e) {
            ui.printSavingError();
        }
    }
}
