package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;

import java.io.IOException;

/**
 * Command class for deleting tasks.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DeleteCommand object.
     * @param taskNumber The (zero-based) index of the task to be deleted
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a task from the task list.
     * @param tasks The task list that the user modifies
     * @param storage Updates when task list is modified
     * @param ui Prints error messages if changes cannot be saved or invalid
     *           task number is encountered.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) {
        try {
            Task task = tasks.getTask(taskNumber);
            ui.printDeleteTask();
            ui.printTask(task);
            tasks.removeTask(taskNumber);
            ui.printSeparator();
            storage.saveData(tasks, ui);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskNumber();
            ui.printSeparator();
        } catch (IOException e) {
            ui.printSavingError();
            ui.printSeparator();
        }
    }
}
