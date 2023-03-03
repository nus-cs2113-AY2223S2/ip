package duke.tasklist.task;

public class Deadline extends Task {
    private static final String DEADLINE_ICON = "D";

    private String by;

    /**
     * Initialises an instance of Deadline task.
     * Stores a task name and end date/time into the instance of Deadline.
     *
     * @param taskName Name of the deadline task.
     * @param by End date/time of the deadline task.
     */
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    /**
     * Returns deadline task formatted as a string for printing to CLI.
     *
     * @return String format of deadline task for printing to CLI.
     */
    @Override
    public String toString() {
        return "[" + DEADLINE_ICON + "]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns deadline task formatted as a string for saving to duke.txt.
     *
     * @return String format of deadline task for saving to duke.txt.
     */
    @Override
    public String toFile() {
        return DEADLINE_ICON + " " + super.toFile() + " | " + by;
    }
}
