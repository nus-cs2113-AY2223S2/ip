package duke.tasks;

/**
 * Represents deadline object that it a type of task
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor of deadline task object, with specified type "D"
     *
     * @param description the description of the current deadline
     * @param by          the deadline of the current deadline object
     */
    public Deadline(String description, String by) {
        super(description);
        this.type = "[D]";
        this.by = by;
    }

    /**
     * Gets the by deadline of the current deadline object
     *
     * @return the deadline of the deadline object
     */
    public String getBy() {
        return by;
    }

    /**
     * Prints the deadline object elements
     *
     * @return the type, status, description and by deadline of current deadline object
     */
    @Override
    public String toString() {
        return super.toString() + getType() + getStatusIcon() + getDescription() + " (by: " + getBy() + ")";
    }
}




