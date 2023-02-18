package duke.task;

/**
 * Contains the description and due date of deadline.
 */
public class Deadline extends Task {

    public String by;

    /**
     * Constructor to initialise task description and due date.
     *
     * @param description Description of the deadline task.
     * @param by          Due date of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string in the required format.
     *
     * @return Task description and due date of deadline in
     * the required format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
