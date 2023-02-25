package app.commands;

import app.exceptions.DukeException;
import app.exceptions.IncompleteCommandException;
import app.exceptions.InvalidCommandException;
import app.exceptions.InvalidTaskException;
import app.parser.AddTaskParser;
import app.save.Storage;
import app.tasks.Deadline;
import app.tasks.Event;
import app.tasks.Task;
import app.tasks.TaskList;
import app.ui.Ui;

public class AddDeadline extends Command {
    private Task newTask;

    public AddDeadline(String commandWord, String commandDescriptor) throws DukeException {
        this.newTask = AddTaskParser.parseCommand(commandWord, commandDescriptor);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(newTask);
        ui.newTaskAddedMessage(newTask, tasks.getTasksCount());
        storage.write(tasks);
    }
}
