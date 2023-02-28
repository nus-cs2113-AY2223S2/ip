package task;

/**
 * Handles the deadline task.
 */
public class Deadline extends Task {
    private String by;

    /**
     * A constructor that accepts the description and /by deadline specified by the user.
     *
     * @param description The description of the deadline.
     * @param by          The date of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Set the toString parameter to print out a readable string.
     *
     * @return The readable string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

    /**
     * Formats the string into a savable string.
     *
     * @return A string that can be saved into a file.
     */
    @Override
    public String savableString() {
        return "D|" + super.savableString() + "|" + by;
    }

}
