package duke.task;

/**
 * Represents event object that it a type of task
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for event task object, with its specified type "E"
     *
     * @param description the description of the current event
     * @param from        the start time of the event
     * @param to          the end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.type = "[E]";
        this.from = from;
        this.to = to;
    }

    /**
     * Gets start time of event object
     *
     * @return the start time of event
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets end time of event object
     *
     * @return the end time of event
     */
    public String getTo() {
        return to;
    }

    /**
     * Prints the event object elements
     *
     * @return the type, status, description, from start time and to end time of current event object
     */
    @Override
    public String toString() {
        return super.toString() + getType() + getStatusIcon() + getDescription()
                + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
