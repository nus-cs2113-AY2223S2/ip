public class Deadline extends Task {
    protected String by;

    /**
     * A task with a deadline.
     * @param description Name of task
     * @param by Deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String byWhen = " (by: " + this.by + ")";
        return "[D]" + super.toString() + byWhen;
    }
}
