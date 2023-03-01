package command;

import jonathan.Storage;
import task.TaskList;
import task.Todo;
import jonathan.Ui;

public class TodoCommand extends Command {
    private final Todo todo;

    public TodoCommand(Todo todo) {
        this.todo = todo;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(todo);
        ui.showAdded(tasks, todo);
        return tasks;
    }
}
