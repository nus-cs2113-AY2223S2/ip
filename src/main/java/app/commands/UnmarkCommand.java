package app.commands;

import app.exceptions.DukeException;
import app.exceptions.IncompleteCommandException;
import app.exceptions.InvalidTaskException;
import app.save.Storage;
import app.tasks.Task;
import app.tasks.TaskList;
import app.ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(String commandDescriptor) throws DukeException {
        parseInput(commandDescriptor);
    }

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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task updatedTask = tasks.unmarkTask(index);
        ui.taskUnmarkedMessage(updatedTask);
        storage.write(tasks);
    }
}
