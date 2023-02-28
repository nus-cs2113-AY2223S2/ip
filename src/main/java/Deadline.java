/**
 * Represents a task which has a deadline.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the type of task.
     * @return "D" representing Deadline task
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns information on when the task should be completed by.
     * @return The deadline of the task
     */
    @Override
    public String getBy() {
        return this.by;
    }

    /**
     * Returns a String representation of the current Deadline task.
     * @return Current task's type, description and deadline in a string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
