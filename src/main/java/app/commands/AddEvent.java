package app.commands;

import app.exceptions.DukeException;
import app.exceptions.IncompleteCommandException;
import app.exceptions.InvalidCommandException;
import app.save.Storage;
import app.tasks.Event;
import app.tasks.Task;
import app.tasks.TaskList;
import app.ui.Ui;

public class AddEvent extends Command {
    private Task newTask;

    public AddEvent(String commandWord, String commandDescriptor) throws DukeException {
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

