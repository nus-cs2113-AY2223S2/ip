package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;

/**
 * Command class for adding tasks of the Event type.
 */
public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs an AddEventCommand object.
     *
     * @param description The description of the Event task
     * @param from        The starting time of the event
     * @param to          The ending time of the event
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds an Event task to the task list.
     *
     * @param tasks   The task list that the user modifies
     * @param storage Updates when task list is modified
     * @param ui      Prints error messages if changes cannot be saved.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) {
        try {
            Task task = new Event(description, from, to);
            tasks.addTask(task);

            ui.printAddTask();
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
