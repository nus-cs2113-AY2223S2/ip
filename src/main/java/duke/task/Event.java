package duke.task;

public class Event extends Task {
    public static final String EVENT_LABEL = "E";
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getType() {
        return "event";
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + EVENT_LABEL + "][" + getStatus() + "] " + description + " (from: " + from + " to: " + to + ")";
    }
}
