package duke.task;

/**
 * The Deadline class extends the Task class
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for a Deadline
     * @param description Task description
     * @param by By description
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * This method returns the deadline in the form of a string
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
