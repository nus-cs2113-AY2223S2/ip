package duke.task;

public class Event extends Task {
    public static final String DELIMITER_FROM = "/from";
    public static final String DELIMITER_TO = "/to";
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return " [E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
