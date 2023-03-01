package task;

public class Event extends Task {
    private final String start;
    private final String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + " (from: " + getStart() +
                " to: " + getEnd() + ")";
    }
}
