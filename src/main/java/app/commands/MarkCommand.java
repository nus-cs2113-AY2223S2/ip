package app.commands;

import app.exceptions.DukeException;
import app.exceptions.IncompleteCommandException;
import app.exceptions.InvalidTaskException;
import app.save.Storage;
import app.tasks.Task;
import app.tasks.TaskList;
import app.ui.Ui;

/**
 * Represents a command to record a task as done and mark it.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor to mark a task.
     * @param commandDescriptor Contains information about which task to mark.
     * @throws DukeException If error in parsing the user input.
     */
    public MarkCommand (String commandDescriptor) throws DukeException {
        parseInput(commandDescriptor);
    }

    /**
     * Method to parse the user input and get the index of the task to be marked.
     * @param commandDescriptor User input minus the first word.
     * @throws DukeException If index is outside the range of the current valid indexes or
     *                       command is incomplete.
     */
    private void parseInput(String commandDescriptor) throws DukeException {
        if (commandDescriptor.length() == 0){
            throw new IncompleteCommandException();
        }
        try {
            this.index = Integer.parseInt(commandDescriptor);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException();
        }
    }

    /**
     * Method to execute the MarkCommand command/
     * @param tasks Task-list containing the existing tasks.
     * @param ui User interface to print relevant information.
     * @param storage Saving of tasks to memory.
     * @throws DukeException If error in saving task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task updatedTask = tasks.markTask(index);
        ui.taskMarkedMessage(updatedTask);
        storage.write(tasks);
    }
}