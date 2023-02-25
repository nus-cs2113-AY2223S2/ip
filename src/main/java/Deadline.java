/**
 * Represents tasks that are labelled as <code>Deadline</code>.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Add label "[D]" and due date "by" to a task.
     * @return tasks in deadline format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

