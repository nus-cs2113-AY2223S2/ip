package Duke.Tasks;

/**
 * Deadline inherits Task with a deadline time added.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " /by " + by;
    }
}

