package app.commands;

import app.exceptions.DukeException;
import app.exceptions.IncompleteCommandException;
import app.exceptions.InvalidCommandException;
import app.exceptions.InvalidTaskException;
import app.save.Storage;
import app.tasks.Deadline;
import app.tasks.Event;
import app.tasks.Task;
import app.tasks.TaskList;
import app.ui.Ui;

/**
 * Represents a new Deadline command.
 */
public class AddDeadline extends Command {
    private Task newTask;

    /**
     * Constructor to add a new deadline.
     * @param commandWord First word of user input.
     * @param commandDescriptor Remaining user input.
     * @throws DukeException If error in parsing the input.
     */
    public AddDeadline(String commandWord, String commandDescriptor) throws DukeException {
        parseInput(commandWord, commandDescriptor);
    }

    /**
     * @param tasks Task-list containing the existing tasks.
     * @param ui User interface to print message.
     * @param storage Saving of tasks to memory.
     * @throws DukeException If error in saving task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(newTask);
        ui.newTaskAddedMessage(newTask, tasks.getTasksCount());
        storage.write(tasks);
    }

    public void parseInput(String commandWord, String commandDescriptor) throws DukeException {
        if (commandDescriptor.length() == 0) {
            throw new IncompleteCommandException(commandWord);
        }
        try {
            String[] parts = commandDescriptor.split("/by");
            String taskDescription = parts[0].trim();
            String deadline = parts[1].trim();
            this.newTask = new Deadline(taskDescription, false, deadline);
        } catch (ArrayIndexOutOfBoundsException e){
            throw new InvalidCommandException();
        }
    }
}
