package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.filemanager.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.util.NoSuchElementException;

/**
 * Handles the delete command to remove task from list.
 */
public class DeleteFromListCommand extends Command {
    private int taskIndex;

    /**
     * Constructor to process userInput and set taskIndex to delete.
     *
     * @param userInput Raw user input containing taskIndex.
     * @throws DukeException Occurs when there is a format error or invalid input.
     */
    public DeleteFromListCommand(String userInput) throws DukeException {
        try {
            String taskIndex = userInput.replace(" ", "");
            setDeleteTask(Integer.parseInt(taskIndex) - 1);
        } catch (NoSuchElementException | NumberFormatException ex) {
            throw new InvalidInputException();
        }
    }

    /**
     * Executes the command to delete and save the deleted task index provided.
     *
     * @param tasks   TaskList of tasks currently stored.
     * @param storage Handler to read write to json.
     * @param ui      Handler to print text to user.
     * @throws DukeException Occurs when there is an invalid input or write error.
     */
    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
            Task task = tasks.deleteTask(taskIndex, storage);
            ui.printDeletedTask(task, tasks);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Enter a number within range of list!");
        }
    }

    /**
     * Sets the taskIndex to delete.
     *
     * @param taskIndex Index that corresponds to task to delete.
     */
    public void setDeleteTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }
}
