package duke.tasklist;

/**
 * Represents a type of task called deadline.
 * This task should be complete by a date or time.
 */
public class Deadline extends Task {
    /**
     * The deadline by which task should be completed.
     */
    protected String by;

    /**
     * Creates a 'deadline' with the given description and deadline.
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline (String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    /**
     * Returns the string representation of this deadline.
     * @return the type, status, task description
     *         and deadline in a string.
     */
    public String toString() {
        return super.toString() + "(by: " + by +")";
    }
}