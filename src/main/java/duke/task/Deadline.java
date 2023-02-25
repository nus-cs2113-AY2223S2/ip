package duke.task;

public class Deadline extends Task {

    /** The end date of the task inputted by the user. */
    protected String by;

    /**
     * Creates a new "deadline" task containing the name of the task and its respective due date.
     *
     * @param description The name/description of the "deadline" task.
     * @param by The due date of the "deadline" task as defined by the user.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string for output printing, containing information about the task.
     *
     * @return String containing the category label of the task, completion status, description of task
     * and dates (if any).
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + by + ")";
    }

    /**
     * Retrieves the due date of the "deadline" task type.
     *
     * @return The ending date of the "deadline" task type in question.
     */
    public String getBy() {
        return by;
    }
}
