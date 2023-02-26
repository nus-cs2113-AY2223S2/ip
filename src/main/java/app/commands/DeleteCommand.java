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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.deleteTask(index);
        ui.taskDeletedMessage(deletedTask, tasks.getTasksCount());
        storage.write(tasks);
    }
}
