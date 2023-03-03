/**
 * Represents a task with a deadline that the user wishes to complete.
 */

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTaskIcon() {
        return "D";
    }

    public String getDeadlineBy() {
        return by;
    }

    /**
     * Returns the Deadline task type and shows whether the task has been completed, as well as the
     * completion deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
