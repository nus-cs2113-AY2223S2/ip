package duke.task;

/**
 * Task object with additional from and to time parameters.
 */
public class Event extends Task {
    public static final String DELIMITER_FROM = "/from";
    public static final String DELIMITER_TO = "/to";
    private String from;
    private String to;

    /**
     * Constructs an event task with the given description and deadline.
     *
     * @param description Description of the deadline task.
     * @param from Time that the event starts at.
     * @param to Time that the event ends at.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the event task into its string representation,
     * which contains its task type, completion status,
     * description, and its start and end times.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return " [E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSaveString() {
        return super.toSaveString("E", isDone ? "1" : "0", description, from, to);
    }
}
