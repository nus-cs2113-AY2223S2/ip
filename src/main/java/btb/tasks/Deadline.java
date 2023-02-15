package btb.tasks;

public class Deadline extends Task {
    protected String dueDate;

    /**
     * Constructor for a new instance of a Task object.
     * Stores the description, dueDate, and status of the new Task.
     * The status is set to false at initialisation.
     *
     * @param description deadline description
     * @param dueDate     the date and time the deadline is up
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * converts the Deadline class to string
     *
     * @return Deadline class as a string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
