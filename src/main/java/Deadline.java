public class Deadline extends Task {
    private final String by;

    /**
     * @param description description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * @return convert to formatted string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
