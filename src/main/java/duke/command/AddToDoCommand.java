package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class AddToDoCommand extends Command {
    private String toDoTaskName;

    /**
     * Initialises an instance of AddToDoCommand.
     * Stores a task name into the instance of AddToDoCommand.
     *
     * @param toDoTaskName Name of the to do task.
     */
    public AddToDoCommand(String toDoTaskName) {
        this.toDoTaskName = toDoTaskName;
    }

    /**
     * Executes add to do task command.
     * Adds a to do task to the task array list.
     * On success display a task added message on the CLI.
     * Save updated task array list into duke.txt.
     *
     * @param tasks   Instance of task list containing task array list.
     * @param ui      Instance of ui.
     * @param storage Instance of storage.
     * @throws IOException if there is an error creating ./data/duke.txt or writing task array list to duke.txt.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTodoTask(toDoTaskName);
        ui.showTaskAdded(tasks.getTasks());
        super.execute(tasks, ui, storage);
    }
}
