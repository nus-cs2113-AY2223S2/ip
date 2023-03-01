package duke.classes;

/**
 * Represents a task with a deadline.
 * Inherits from Task class.
 */
public class Deadline extends Task {

    /** Deadline of the task */
    protected String by;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a formatted string representation of the task.
     * Overrides the toString() method in the Task class.
     *
     * @return Formatted string representation of the task.
     */
    @Override
    public String toString() {

        return "[D][" + getStatusIcon() + "] " + super.toString() + " (" + by + ")";
    }
}
