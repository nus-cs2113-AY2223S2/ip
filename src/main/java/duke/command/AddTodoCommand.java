package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;

/**
 * Command class for adding tasks of the Todo type.
 */
public class AddTodoCommand extends Command {
    private String description;

    /**s
     * Constructs an AddTodoCommand object.
     * @param description The description of the Todo task
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a Todo task to the task list.
     * @param tasks The task list that the user modifies
     * @param storage Updates when task list is modified
     * @param ui Prints error messages if changes cannot be saved.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) {
        try {
            ui.printAddTask();
            Task task = new Todo(description);
            tasks.addTask(task);
            ui.printTask(task);
            ui.printNoOfTasks(tasks.getSize());
            ui.printSeparator();
            storage.saveData(tasks, ui);
        } catch (IOException e) {
            ui.printSavingError();
            ui.printSeparator();
        }
    }
}
