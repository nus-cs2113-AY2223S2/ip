package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class AddDeadlineCommand extends Command {
    private String deadlineTaskName;
    private String by;

    /**
     * Initialises an instance of AddDeadlineCommand.
     * Stores a task name and end date/time into the instance of AddDeadlineCommand.
     *
     * @param deadlineTaskName Name of the deadline task.
     * @param by End date/time of the deadline task.
     */
    public AddDeadlineCommand(String deadlineTaskName, String by) {
        this.deadlineTaskName = deadlineTaskName;
        this.by = by;
    }

    /**
     * Executes add deadline task command.
     * Adds a deadline task to the task array list.
     * On success display a task added message on the CLI.
     * Save updated task array list into duke.txt.
     *
     * @param tasks Instance of task list containing task array list.
     * @param ui Instance of ui.
     * @param storage Instance of storage.
     * @throws IOException if there is an error creating ./data/duke.txt or writing task array list to duke.txt.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addDeadlineTask(deadlineTaskName, by);
        ui.showTaskAdded(tasks.getTasks());
        super.execute(tasks, ui, storage);
    }
}