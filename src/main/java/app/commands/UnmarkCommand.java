package app.commands;

import app.exceptions.DukeException;
import app.exceptions.IncompleteCommandException;
import app.exceptions.InvalidTaskException;
import app.save.Storage;
import app.tasks.Task;
import app.tasks.TaskList;
import app.ui.Ui;

/**
 * Represents a command to record a task as not done and unmark it.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructor to unmark a task.
     * @param commandDescriptor Contains information about which task to unmark.
     * @throws DukeException If error in parsing the user input.
     */
    public UnmarkCommand(String commandDescriptor) throws DukeException {
        parseInput(commandDescriptor);
    }

    /**
     * Method to parse the user input and get the index of the task to be unmarked.
     * @param commandDescriptor User input minus the first word.
     * @throws DukeException If index is outside the range of the current valid indexes or
     *                       command is incomplete.
     */
    private void parseInput(String commandDescriptor) throws DukeException {
        if (commandDescriptor.length() == 0) {
            throw new IncompleteCommandException();
        }
        try {
            this.index = Integer.parseInt(commandDescriptor);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException();
        }
    }

    /**
     * Method to execute the UnmarkCommand command.
     * @param tasks Task-list containing the existing tasks.
     * @param ui User interface to print relevant information.
     * @param storage Saving of tasks to memory.
     * @throws DukeException If error in saving task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task updatedTask = tasks.unmarkTask(index);
        ui.taskUnmarkedMessage(updatedTask);
        storage.write(tasks);
    }
}
