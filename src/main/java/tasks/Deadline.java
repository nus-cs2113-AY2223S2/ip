package tasks;

/**
 * A <code>Deadline</code> class that represents a deadline task.
 */
public class Deadline extends Task {
    /**
     * The deadline of the task.
     */
    public String by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description the description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the type of the task: <code>D</code> for deadline.
     *
     * @return the type of the task, which is <code>D</code>
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns a string representation of the deadline task, including its type, description, and deadline.
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "]" + super.toString() + "(by: " + by + ")";
    }
}