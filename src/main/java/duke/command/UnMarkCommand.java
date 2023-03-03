package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class UnMarkCommand extends Command {
    private int taskNo;

    /**
     * Initialises an instance of UnMarkCommand.
     * Stores a task no into the instance of UnMarkCommand.
     *
     * @param taskNo Task number of a task in the task list.
     */
    public UnMarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes unmark task command.
     * Unark a task from the task array list.
     * On success display task incomplete message on the CLI.
     * Save updated task array list into duke.txt.
     *
     * @param tasks Instance of task list containing task array list.
     * @param ui Instance of ui.
     * @param storage Instance of storage.
     * @throws IOException if there is an error creating ./data/duke.txt or writing task array list to duke.txt.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.unmarkTask(taskNo);
        ui.showTaskIncomplete(tasks.getTasks(), taskNo);
        super.execute(tasks, ui, storage);
    }
}
