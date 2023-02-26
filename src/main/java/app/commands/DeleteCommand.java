package app.commands;

import app.exceptions.DukeException;
import app.exceptions.IncompleteCommandException;
import app.exceptions.InvalidTaskException;
import app.save.Storage;
import app.tasks.Task;
import app.tasks.TaskList;
import app.ui.Ui;

/**
 * Represents a command to delete a task from Task-list.
 */
public class DeleteCommand extends Command{
    private int index;

    /**
     * Constructor to delete a task.
     * @param commandDescriptor Contains information about which task to delete.
     * @throws DukeException If error in parsing the input.
     */
    public DeleteCommand(String commandDescriptor) throws DukeException {
        parseInput(commandDescriptor);
    }

    /**
     * Method to parse the input of user to get index of task to delete.
     * @param commandDescriptor User input minus the first word.
     * @throws DukeException If index is outside the range of the current valid indexes or
     *                       command is incomplete.
     */
    private void parseInput(String commandDescriptor) throws DukeException{
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
     * Method to execute the DeleteCommand command.
     * @param tasks Task-list containing the existing tasks.
     * @param ui User interface to print message.
     * @param storage Saving of tasks to memory.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.deleteTask(index);
        ui.taskDeletedMessage(deletedTask, tasks.getTasksCount());
        storage.write(tasks);
    }
}
