package duke.task;

/**
 * This object represents a task object of event type.
 * It contains a <code>to</code> and a <code>from</code> which represents the start time
 * and the end time of the task.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from.substring(5);
        this.to = to.substring(3);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + "to: " + to + ")";
    }
}

