package ChatTPG;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String start) {
        from = start;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String end) {
        to = end;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
