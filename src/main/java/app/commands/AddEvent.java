package app.commands;

import app.exceptions.DukeException;
import app.exceptions.IncompleteCommandException;
import app.exceptions.InvalidCommandException;
import app.save.Storage;
import app.tasks.Event;
import app.tasks.Task;
import app.tasks.TaskList;
import app.ui.Ui;

/**
 * Represents a new Event command.
 */
public class AddEvent extends Command {
    private Task newTask;

    /**
     * Constructor to add a new event.
     * @param commandWord First word of user input.
     * @param commandDescriptor Remaining user input.
     * @throws DukeException If error in parsing the input.
     */
    public AddEvent(String commandWord, String commandDescriptor) throws DukeException {
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
            String[] fromParts = commandDescriptor.split("/from");
            String taskDescription = fromParts[0].trim();
            String[] toParts = fromParts[1].split("/to");
            String startTime = toParts[0].trim();
            String endTime = toParts[1].trim();
            this.newTask = new Event(taskDescription, false, startTime, endTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        }
    }
}

