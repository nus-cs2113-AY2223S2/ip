package command;

import task.Todo;
import components.TaskList;
import components.UI;
import components.Storage;

public class AddTodoCommand extends Command {

    public AddTodoCommand(String[] commandFields) {
        super(commandFields);
    }

    /**
     * Adds and writes task of <code>Todo</code> to the ArrayList.
     *
     * @param tasks ArrayList of tasks.
     * @param ui Deals with interactions with the user.
     * @param storage Deals with saving and loading tasks in the file.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.tasks.add(new Todo(commandFields[0]));
        ui.taskAdded(tasks.tasks);
        storage.writeToFile(tasks.tasks, storage.filePath);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
