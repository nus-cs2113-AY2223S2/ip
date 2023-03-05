package duke.task;

/**
 * Represents a deadline task where a deadline for a task is included
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Instantiates the deadline task variables
     *
     * @param description description of task
     * @param by deadline date of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Instantiates the deadline task variables
     *
     * @param description description of task
     * @param by deadline date of task
     * @param isDone if task is marked as done
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Overrides toString method to return output as shown in list of tasks
     *
     * @return string of deadline in listed format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
