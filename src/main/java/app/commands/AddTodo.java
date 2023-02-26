package app.commands;

import app.exceptions.DukeException;
import app.parser.AddTaskParser;
import app.save.Storage;
import app.tasks.Task;
import app.tasks.TaskList;
import app.ui.Ui;

public class AddTodo extends Command {
    private Task newTask;
    public AddTodo(String commandWord, String commandDescriptor) throws DukeException {
        this.newTask = AddTaskParser.parseCommand(commandWord, commandDescriptor);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(newTask);
        ui.newTaskAddedMessage(newTask, tasks.getTasksCount());
        storage.write(tasks);
    }
}
