package app.commands;

import app.exceptions.DukeException;
import app.exceptions.IncompleteCommandException;
import app.save.Storage;
import app.tasks.Task;
import app.tasks.TaskList;
import app.tasks.ToDo;
import app.ui.Ui;

public class AddTodo extends Command {
    private Task newTask;

    public AddTodo(String commandWord, String commandDescriptor) throws DukeException {
        parseInput(commandWord, commandDescriptor);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(newTask);
        ui.newTaskAddedMessage(newTask, tasks.getTasksCount());
        storage.write(tasks);
    }

    public void parseInput(String commandWord, String commandDescriptor) throws DukeException{
        if (commandDescriptor.length() == 0) {
            throw new IncompleteCommandException(commandWord);
        }
        this.newTask = new ToDo(commandDescriptor, false);
    }
}
