package duke.task;

/**
 * Represents an Event task
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task.
     * @param description The description of the event
     * @param from The starting time of the event
     * @param to The ending time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    /**
     * Returns a string for the Event task.
     * @return String indicating the task type, event description and event duration.
     */
    public String toString() {
        return "[E]" + super.toString() + description + " (from: " + from + " to: " + to + ")";
    }
}
