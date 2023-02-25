public class Deadline extends Task {
    /***
     * Due date of the task.
     */
    protected String by;

    /***
     * Functions in the Deadline class.
     * @param description Name of the task.
     * @param by Due date of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /***
     * The string output when class is called.
     * @return Status and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
