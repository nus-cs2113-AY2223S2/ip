package duke;
public class Deadline extends Task {
    /**
     * Creates a deadline task.
     * @param description is the task name
     * @param by is the date the task is due by
     * @return toString the format of how the task is shown
     */
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

