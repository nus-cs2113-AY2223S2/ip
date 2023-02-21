/**
 * Class for a Deadline Task.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructor for Deadline task
     *
     * @param name the name of the task
     * @param by the deadline of the task
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    /**
     * Overrides the string to the format of a deadline task
     *
     * @return a string representing the details of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}