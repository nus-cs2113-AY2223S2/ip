package duke.Commands;

import duke.Storage;
import dukeException.DukeException;
import tasks.Deadline;
import tasks.Event;

/**
 * Represents an EventCommand that is used to add an event to the task list.
 * Inherits from the Command class.
 */
public class EventCommand extends Command {
    /**
     * The Event object associated with the EventCommand.
     */
    private Event event;

    /**
     * The keyword associated with the EventCommand.
     */
    public static final String COMMAND_WORD = "event";

    /**
     * The description of the event.
     */
    private final String desc;

    /**
     * The completion status of the event.
     */
    private final boolean isMark;

    /**
     * The start time of the event.
     */
    private final String start;

    /**
     * The end time of the event.
     */
    private final String end;

    /**
     * Constructs an EventCommand object with the specified description,
     * completion status, start and end times of the event.
     *
     * @param desc   The description of the event.
     * @param isMark The completion status of the event.
     * @param start  The start time of the event.
     * @param end    The end time of the event.
     */
    public EventCommand(String desc, boolean isMark, String start, String end) {
        this.desc = desc;
        this.isMark = isMark;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the EventCommand by creating a new Event object with the
     * specified description, completion status, start and end times,
     * adding it to the task list, and printing out a message
     * indicating that the event has been added to the list.
     *
     * @throws DukeException if there is an error creating the Event object.
     */
    public void cmd() throws DukeException {

        Event event = new Event(this.desc, this.isMark, this.start, this.end);
        tasks.add(event);
        addTaskPrint(tasks, event);
        Storage.saveTasks(tasks);
    }
}
