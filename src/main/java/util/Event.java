package util;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    @Override
    public String toStringForSave() {
        String doneStatus = super.isDone() ? "1" : "0";
        return String.format("E | %s | %s | %s | %s", doneStatus, super.getDescription(), from, to);
    }
}
