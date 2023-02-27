package util;

/**
 * 
 * A class that represents a task with a deadline.
 */

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object, including its
     * description and deadline.
     * 
     * @return the string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * @return the string representation of the Deadline object for saving purposes.
     */
    @Override
    public String toStringForSave() {
        String doneStatus = super.isDone() ? "1" : "0";
        return String.format("D | %s | %s | %s", doneStatus, super.getDescription(), by);
    }
}