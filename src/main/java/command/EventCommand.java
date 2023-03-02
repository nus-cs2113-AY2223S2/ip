package command;

import task.Event;
import task.TaskList;
import ui.Ui;
import utils.Storage;

/**
 *  Command for adding an event task.
 *  Format: event [description] /from [time] /to [time]
 */
public class EventCommand extends Command{
    protected String eventString;
    protected String fromString;
    protected String toString;



    public EventCommand(String eventString, String fromString, String toString) {
        this.eventString = eventString;
        this.fromString = fromString;
        this.toString = toString;

    }

    /**
     * Execute the event command. Add a new event object to the task list,
     * and write it to the disk.
     * @param tasks The task list.
     * @param ui The ui module to show messages.
     * @param storage The reading and writing tool.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){

        Event newEventObject = new Event(eventString, fromString, toString);
        tasks.add(newEventObject);
        storage.addNewObjectToFile(newEventObject, ui);

        ui.showEventTask(newEventObject, tasks);
    }

}
