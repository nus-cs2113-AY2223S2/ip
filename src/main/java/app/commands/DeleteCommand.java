package app.commands;

import app.exceptions.DukeException;
import app.exceptions.IncompleteCommandException;
import app.exceptions.InvalidTaskException;
import app.save.Storage;
import app.tasks.Task;
import app.tasks.TaskList;
import app.ui.Ui;

public class DeleteCommand extends Command{
    private int index;
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
