package duke.tasks;

/**
 * Represents a deadline Task
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for deadline task represented with specified type "D"
     *
     * @param description the description of the deadline task
     * @param by          the timing the task is due
     */
    public Deadline(String description, String by) {
        super(description);
        this.type = "[D]";
        this.by = by;
    }

    /**
     * Getter function for the timing it is due
     *
     * @return the timing it is due
     */
    public String getBy() {
        return by;
    }

    /**
     * Output the deadline task and its details
     *
     * @return the type, status, description and timing it is due of current deadline task
     */
    @Override
    public String toString() {
        return super.toString() + getType() + getStatusIcon() + getDescription() + " (by: " + getBy() + ")";
    }
}




