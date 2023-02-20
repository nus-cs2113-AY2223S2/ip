package duke.task;

/**
 * Task object with an additional deadline time parameter.
 */
public class Deadline extends Task {
    public static final String DELIMITER_BY = "/by";
    private String by;

    /**
     * Constructs a deadline task with the given description and deadline.
     *
     * @param description Description of the deadline task.
     * @param by Time that the task should be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the deadline task into its string representation,
     * which contains its task type, completion status,
     * description, and the time it should be completed by.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSaveString() {
        return super.toSaveString("D", isDone ? "1" : "0", description, by);
    }
}
