package duke.tasks;

/**
 * Class creates a Deadline type task.
 */
public class Deadline extends Task {

    private String by;

    /**
     * Creates a Deadline object and initializes by.
     *
     * @param description Description of deadline user wants to add.
     * @param by Date of the deadline.
     * @param isDone Tells if this deadline has been completed.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the task type of deadline.
     *
     * @return Task type.
     */
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns the date of the deadline.
     *
     * @return Deadline date.
     */
    public String getDeadline() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
