package duke;

/**
 * Represents a type of task that has a due date, which is indicated by
 * the user, by the delimiter "/by".
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a Deadline object that inherits from Task object, assigns the due date of task.
     *
     * @param description The description of the deadline.
     * @param by The due date of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Prints Deadline object in a specific format.
     *
     * @return A string representing the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";}

}
