package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Task;

import java.io.IOException;

/**
 * Command class for marking a task as not done.
 */
public class UnmarkTaskCommand extends Command {
    private int taskNumber;

    /**
     * Constructs an UnmarkTaskCommand object.
     * @param taskNumber The (zero-based) index of the task to be marked as not done
     */
    public UnmarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks task as not done and saves any changes made to the task list.
     * @param tasks The task list that the user modifies
     * @param storage Updates when task list is modified
     * @param ui Prints error messages if task number is invalid, task is already
     *           marked as not done, or there is an error when saving.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) {
        try {
            Task task = tasks.getTask(taskNumber);
            task.markAsNotDone();
            ui.printMarkNotDone();
            ui.printTask(task);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskNumber();
        } catch (DukeException e) {
            ui.printTaskAlreadyUnmarked();
        }

        try {
            storage.saveData(tasks, ui);
        } catch (IOException e) {
            ui.printSavingError();
        }
    }
}
