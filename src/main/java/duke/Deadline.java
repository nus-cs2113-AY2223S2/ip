package duke;

public class Deadline extends Task {

    protected String by;

    /**
     * Creates a new Deadline object
     *
     * @param description the description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline object
     */
    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] "+ this.description + " (by:" + by + ")";
    }

    /**
     * Returns the string representation of the Deadline object to be saved in the
     */
    @Override
    public String saveTask() {
        return ("D" + "//" + checkCompletion() + "//" + getDescription() + "//" + this.by + "\n");
    }
}
