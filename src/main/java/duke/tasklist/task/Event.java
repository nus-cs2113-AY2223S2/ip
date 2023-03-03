package duke.tasklist.task;

public class Event extends Task {
    private static final String EVENT_ICON = "E";

    private String from;
    private String to;

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + EVENT_ICON + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFile() {
        return EVENT_ICON + " " + super.toFile() + " | " + from + " | " + to;
    }

}
