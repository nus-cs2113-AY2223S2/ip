package duke;

public class Event extends Task {
    public static final String EVENT_ICON = "[E]";
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return EVENT_ICON + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String saveFormat() {
        return "E|" + super.saveFormat() + "|" + from + "|" + to;
    }
}

