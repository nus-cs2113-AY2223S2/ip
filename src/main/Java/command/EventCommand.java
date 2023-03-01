package command;

import task.Event;
import jonathan.Storage;
import task.TaskList;
import jonathan.Ui;

/**
 * model a class to handle the event command. inherit from Command class.
 */
public class EventCommand extends Command {
    private final Event event;

    /**
     * build constructor for EventCommand class.
     * @param event task to be executed.
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Method to execute the event command class.
     * @param tasks list of tasks.
     * @param ui the interface of the program.
     * @param storage the storage of the program.
     * @return list of tasks.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(event);
        ui.showAdded(tasks, event);
        return tasks;
    }
}
