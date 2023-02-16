package duke;

/**
 * Represents a Deadline type of task that has a due date, which is keyed in by the user
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String details, String by) {
        super(details);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +   " (by: " + this.by + ")";
    }
}
