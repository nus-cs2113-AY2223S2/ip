package duke.commands;

import duke.data.Event;

/**
 * Adds an event to the TaskList.
 */
public class AddEventCommand extends Command{
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = "OOPS!! The correct format of adding event is: event task /from time /to time";
    private final Event toAdd;

    /**
     * Convenience constructor using raw values.
     * @param content the description of the event
     * @param fromTime the starting time of the event
     * @param toTime the ending time of the event
     */
    public AddEventCommand(String content, String fromTime, String toTime) {
        this.toAdd = new Event(content, fromTime, toTime);
    }

    public Event getEvent() {
        return toAdd;
    }

    public void execute() {
        taskList.addTask(toAdd);
        System.out.println("Got it. I've added this task: ");
        System.out.println(toAdd);
        taskList.printSize();
    }
}
