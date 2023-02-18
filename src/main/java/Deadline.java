/**
 * Represents a type of task with a deadline.
 */
public class Deadline extends Task {

    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the letter denoting the type of task.
     *
     * @return "D" denoting deadline.
     */
    @Override
    public String getTypeOfTask() {
        return "D";
    }

    /**
     * Returns the formatted string to be displayed to user without prepended information.
     *
     * @return formatted deadline information for user.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + this.by + ")";
    }

    /**
     * Returns the formatted string to be saved in duke.txt.
     *
     * @return formatted deadline information.
     */
    @Override
    public String getDetailsToSave() {
        return super.description + " /by " + this.by;
    }

    /**
     * Returns the formatted string to be displayed to user.
     *
     * @return formatted deadline information for user.
     */
    @Override
    public String toString() {
        return "       [D][ ] " + super.description + " (by: " + this.by + ")";
    }
}
