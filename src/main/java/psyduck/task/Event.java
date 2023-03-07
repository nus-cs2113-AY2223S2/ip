package psyduck.task;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
