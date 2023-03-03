package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskNo;

    /**
     * Initialises an instance of DeleteCommand.
     * Stores a task no into the instance of DeleteCommand.
     *
     * @param taskNo Task number of a task in the task list.
     */
    public DeleteCommand (int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes delete task command.
     * Displays task deleted message on the CLI.
     * Deletes a task from the task array list.
     * Save updated task array list into duke.txt.
     *
     * @param tasks Instance of task list containing task array list.
     * @param ui Instance of ui.
     * @param storage Instance of storage.
     * @throws IOException if there is an error creating ./data/duke.txt or writing task array list to duke.txt.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showTaskDeleted(tasks.getTasks(), taskNo);
        tasks.deleteTask(taskNo);
        super.execute(tasks, ui, storage);
    }
}
