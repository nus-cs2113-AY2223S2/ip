package buddy.tasks;

public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for Deadline class which is a type of Task
     *
     * @param description Description of deadline
     * @param by          Deadline date in YYYY-MM-DD format
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDeadline() {
        return by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (Please do by: " + by + "!)";
    }
}