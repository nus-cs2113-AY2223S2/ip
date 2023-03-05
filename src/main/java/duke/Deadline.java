package duke;

/**
 * Represents a task that is of type deadline.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Sets the description, done status and date of the deadline.
     *
     * @param description description of the deadline.
     * @param isDone done status of the deadline.
     * @param by date of the deadline.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description,isDone);
        this.by = by;
    }

    public String getType(){
        return "D";
    }

    public String getDeadline(){
        return " (by: " + by + ")";
    }
    public String getDeadlineSave(){
        return by;
    }
    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + "  [D]" + super.toString() + " (by: " + by + ")";
    }
}
