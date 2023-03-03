package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private int taskNo;

    /**
     * Initialises an instance of MarkCommand.
     * Stores a task no into the instance of MarkCommand.
     *
     * @param taskNo Task number of a task in the task list.
     */
    public MarkCommand (int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes mark task command.
     * Mark a task from the task array list.
     * On success display task completed message on the CLI.
     * Save updated task array list into duke.txt.
     *
     * @param tasks Instance of task list containing task array list.
     * @param ui Instance of ui.
     * @param storage Instance of storage.
     * @throws IOException if there is an error creating ./data/duke.txt or writing task array list to duke.txt.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.markTask(taskNo);
        ui.showTaskCompleted(tasks.getTasks(), taskNo);
        super.execute(tasks, ui, storage);
    }
}
