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

public class AddDeadline extends Command {
    private Task newTask;

    public AddDeadline(String commandWord, String commandDescriptor) throws DukeException {
        parseInput(commandWord, commandDescriptor);
    }

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
