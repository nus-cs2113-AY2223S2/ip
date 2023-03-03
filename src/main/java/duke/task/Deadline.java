package duke.task;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task
     * @param description The description of what is to be done by the deadline
     * @param by The timing of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    /**
     * Returns a string for the Deadline task
     * @return String indicating the task type, task description and timing of the deadline.
     */
    public String toString() {
        return "[D]" + super.toString() + description + " (by:" + by + ")";
    }
}
