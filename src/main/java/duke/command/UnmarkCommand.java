package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.filemanager.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Handles the unmark command to mark a task as undone.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructor to set the task index to unmark.
     *
     * @param userInput Raw input containing task index to unmark.
     * @throws DukeException Occurs when there is an invalid input.
     */
    public UnmarkCommand(String userInput) throws DukeException {
        try {
            String taskIndex = userInput.replace(" ", "");
            setUnmarkTask(Integer.parseInt(taskIndex) - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            throw new InvalidInputException();
        }
    }

    /**
     * Executes the command to unmark the task given with the taskIndex.
     *
     * @param tasks   TaskList of tasks currently stored.
     * @param storage Handler to read write to json.
     * @param ui      Handler to print text to user.
     * @throws DukeException Occurs when there is an invalid input.
     */
    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
            Task task = tasks.markAsUndone(taskIndex, storage);
            ui.printUnmarkedTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number within list size!");
        }
    }

    /**
     * Sets the taskIndex to unmark.
     *
     * @param taskIndex index of task to unmark.
     */
    public void setUnmarkTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }


}
