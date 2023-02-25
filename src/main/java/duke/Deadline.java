package duke;

/**
 * Represents an extension of a task with a deadline.
 * It is a task including the deadline of the task.
 */

public class Deadline extends Task {
    /**
     * Creates a deadline task.
     * @param description is the task name
     * @param by is the date the task is due by
     */
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * To display the task in a specific way
     * @return the format of how the task is shown
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

