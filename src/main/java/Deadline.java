/**
 * Represents tasks that need to be done before a specific date/time.
 * e.g., submit report by 11/10/2019 5pm.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor initializing the content and deadline of the Deadline task.
     * The task is unmarked by default.
     * @param content content of the Deadline task
     * @param by deadline of the Deadline task
     */
    public Deadline(String content, String by) {
        super(content);
        this.by = by;
    }

    /**
     * Converts the task to a string with label, mark status and deadline.
     * @return a string containing the task's label, mark status and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
