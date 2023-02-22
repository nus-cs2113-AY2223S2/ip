package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.filemanager.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Marks a task as completed
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructor which further processes the userInput to remove whitespace to
     * set taskIndex
     *
     * @param userInput raw input containing taskIndex
     * @throws DukeException Occurs when there is an invalid input
     */

    public MarkCommand(String userInput) throws DukeException {
        try {
            String taskIndex = userInput.replace(" ", "");
            setMarkTask(Integer.parseInt(taskIndex) - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            throw new InvalidInputException();
        }

    }

    /**
     * Executes the command to mark a task as completed
     *
     * @param tasks   TaskList of tasks currently stored
     * @param storage Handler to read write to json
     * @param ui      Handler to print text to user
     * @throws DukeException Occurs when there is a write error or format error
     */
    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
            Task task = tasks.markAsDone(taskIndex, storage);
            ui.printMarkedTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number within list size!");
        }
    }

    /**
     * Sets the taskIndex of the task to be marked
     *
     * @param taskIndex Index of task to be marked
     */
    public void setMarkTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }


}
