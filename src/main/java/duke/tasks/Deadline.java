package duke.tasks;

/**
 * One of the three task type (todo, deadline, event)
 */
public class Deadline extends Task {
    private String by;

    /**
     * @param description tasks name
     * @param by          deadline of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDeadline() {
        return this.by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }


}
