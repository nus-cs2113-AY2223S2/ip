package task;

/**
 * Represents a Deadline type of task that has a due date, which is keyed in by the user
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String details, String by) {
        super(details);
        this.by = by;
    }

    /**
     * Getter method that gets the 'by' attribute of the Deadline object.
     *
     * @return a string that contains the description of the 'by' attribute.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Method to print Deadline objects in a certain manner.
     *
     * @return a string that represents the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() +   " (by: " + this.by + ")";
    }
}
