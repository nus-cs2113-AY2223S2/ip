package command;

import jonathan.Storage;
import task.TaskList;
import task.Todo;
import jonathan.Ui;

/**
 * model a class to handle the todo command. inherit from Command class.
 */
public class TodoCommand extends Command {
    private final Todo todo;

    /**
     * build constructor for TodoCommand class.
     * @param todo task to be executed.
     */
    public TodoCommand(Todo todo) {
        this.todo = todo;
    }

    /**
     * Method to execute the todo command class.
     * @param tasks list of tasks.
     * @param ui the interface of the program.
     * @param storage the storage of the program.
     * @return list of tasks.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(todo);
        ui.showAdded(tasks, todo);
        return tasks;
    }
}
