package duke.task;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(int Index, String description, String from, String to) {
        super(Index, description);
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
        return super.toString() + "[E]" + "[" + getStatusIcon() + "] " + getDescription()
                + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
