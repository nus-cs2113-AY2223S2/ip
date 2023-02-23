package duke.tasks;

/***
 * The Deadline Type Task Class
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Creates Deadline Task
     *
     * @param description The Deadline Description
     * @param by The Deadline Due Date
     * */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns The Deadline Task
     *
     * @return Deadline Task
     * */
    @Override
    public String toString() {
        return "  [D]" + "[" + getStatusIcon() +"] " + super.getDescription() + "(by " + by + ")";
    }
}