package duke;

/**
 * Represents an upcoming deadline. A <code>Deadline</code> object corresponds to
 * a task with a corresponding due date.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Initializes Deadline class.
     *
     * @param description Description of deadline.
     * @param by Date/time when deadline is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    /**
     * Returns standardized string to be stored on memory that can be read back into the program in the future.
     *
     * @return Standardized string to be stored in memory.
     */
    public String storeString() {
        return super.storeString() + "|D|" + description + "|" + by + "\n";
    }

    @Override
    public boolean matchesKeyword (String keyword) {
        if (description.contains(keyword) || by.contains(keyword)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    /**
     * Overrides string representation for deadlines, with a [D] to
     * indicate deadline class and including by field.
     *
     * @return String Representation.
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
