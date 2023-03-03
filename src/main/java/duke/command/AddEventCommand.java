package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class AddEventCommand extends Command {
    private String eventTaskName;
    private String from;
    private String to;

    /**
     * Initialises an instance of AddEventCommand.
     * Stores a task name, start date/time and end date/time into the instance of AddEventCommand.
     *
     * @param eventTaskName Name of the event task.
     * @param from Start date/time of the event task.
     * @param to End date/time of the event task.
     */
    public AddEventCommand (String eventTaskName, String from, String to) {
        this.eventTaskName = eventTaskName;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes add event task command.
     * Adds an event task to the task array list.
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
        tasks.addEventTask(eventTaskName, from, to);
        ui.showTaskAdded(tasks.getTasks());
        super.execute(tasks, ui, storage);
    }
}
