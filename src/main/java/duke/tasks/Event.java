package duke.tasks;

public class Event extends Task {
    public static final String MARKER = "E";
    private final String from, to;

    public Event(String description, String from, String to) {
        super(description, TaskEnum.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String describe() {
        return getCheckbox(true, MARKER)
                + super.describe()
                + " (from: " + from + " to: " + to + ")";
    }
}
