package duke;

/**
 * Represents an Event type of task that has a timeframe, from a certain date/time
 * to a certain date/time, which is keyed in by the user.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String details, String from, String to) {
        super(details);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +   " (from: " + this.from + " to: " + this.to + ")";
    }
}
