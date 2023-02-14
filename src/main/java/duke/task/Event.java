package duke.task;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.type = "[E]";
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return super.toString() + getType() + getStatusIcon() + getDescription()
                + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
