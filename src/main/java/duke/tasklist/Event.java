package duke.tasklist;

/**
 * Represents a type of task called event.
 * This task happens within a stipulated date or time.
 */
public class Event extends Task{
    /**
     * The starting date or time of the event.
     */
    protected String from;
    /**
     * The ending date or time of the event.
     */
    protected String to;

    /**
     * Creates an 'event' with the given description, start and end time.
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    /**
     * Returns the string representation of this event.
     * @return the type, status, task description
     *         and duration in a string.
     */
    public String toString() {
        return super.toString() + "(from: " + from.replace("from ", "") + " to: " + to + ")";
    }
}
