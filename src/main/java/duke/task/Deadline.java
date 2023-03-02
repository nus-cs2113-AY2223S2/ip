package duke.task;

/**
 * This object represents a task object of deadline type.
 * It contains a <code>by</code> which represents the deadline of the task.
 */
public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides the template task toString with additional attribute <code>by</code> and returns it as a string.
     *
     * @return Return the task object's string representation with its description, and its deadline wrapped in brackets.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
