package tasks;

public class Deadline extends Task{
    protected String by;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Class constructor with <code>description</code> and
     * <code>doneby</code> as parameters for initialization.
     *
     * @param description the description of the task.
     * @param doneBy the due date of the task.
     */
    public Deadline(String description, String doneBy) {
        super(description, "D");
        by = doneBy;
    }
    @Override
    public String toString() {
        return '[' + super.getType() + "]" + super.toString() + "(by: " + by + ')';
    }

}
