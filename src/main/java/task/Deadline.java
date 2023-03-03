package task;

/**
 * Represent a Deadline task.
 */
public class Deadline extends Task {
    private final String due;

    public String getDue() {
        return due;
    }

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    /**
     * Get the character representing the type "Deadline".
     *
     * @return "D"
     */
    public String getTaskType() {
        return "D";
    }

    /**
     * Get the summary of the Deadline in the format used to display to the user.
     *
     * @return the summary of the Deadline in UI display format.
     */
    @Override
    public String getSummary() {
        return super.getSummary() + " (by: " + getDue() + ")";
    }

    /**
     * Get the summary of the Deadline in the format used to store in duke.txt.
     *
     * @return the summary of the Deadline in storage duke.txt format.
     */
    @Override
    public String getDataSummary() {
        return super.getDataSummary() + " | " + getDue();
    }
}
