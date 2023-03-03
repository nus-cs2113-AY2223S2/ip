package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Task;

import java.io.IOException;

/**
 * Command class for marking a task as done.
 */
public class MarkTaskCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a MarkTaskCommand object.
     * @param taskNumber The (zero-based) index of the task to be marked as done
     */
    public MarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks task as done and saves any changes made to the task list.
     * @param tasks The task list that the user modifies
     * @param storage Updates when task list is modified
     * @param ui Prints error messages if task number is invalid, task is already
     *           marked as done, or there is an error when saving.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) {
        try {
            Task task = tasks.getTask(taskNumber);
            task.markAsDone();
            ui.printMarkDone();
            ui.printTask(task);
            ui.printSeparator();
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskNumber();
            ui.printSeparator();
        } catch (DukeException e) {
            ui.printTaskAlreadyMarked();
            ui.printSeparator();
        }

        try {
            storage.saveData(tasks, ui);
        } catch (IOException e) {
            ui.printSavingError();
            ui.printSeparator();
        }
    }
}
