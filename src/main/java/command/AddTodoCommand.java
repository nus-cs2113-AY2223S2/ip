package command;

import task.Todo;
import components.TaskList;
import components.Ui;
import components.Storage;

public class AddTodoCommand extends Command {

    public AddTodoCommand(String[] commandFields) {
        super(commandFields);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.tasks.add(new Todo(commandFields[0]));
        ui.taskAdded(tasks.tasks);
        storage.writeToFile(tasks.tasks, storage.filePath);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
