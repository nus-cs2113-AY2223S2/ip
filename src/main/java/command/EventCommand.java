package command;

import exceptions.EventParamsFormatException;
import task.Event;
import task.TaskList;
import ui.Ui;
import utils.Storage;

import java.io.FileWriter;
import java.io.IOException;

public class EventCommand extends Command{
    protected String eventString;
    protected String fromString;
    protected String toString;



    public EventCommand(String eventString, String fromString, String toString) {
        this.eventString = eventString;
        this.fromString = fromString;
        this.toString = toString;

    }

    public void execute(TaskList tasks, Ui ui, Storage storage){

        Event newEventObject = new Event(eventString, fromString, toString);
        tasks.add(newEventObject);
        storage.addNewObjectToFile(newEventObject, ui);

        ui.showEventTask(newEventObject, tasks);
    }

}
