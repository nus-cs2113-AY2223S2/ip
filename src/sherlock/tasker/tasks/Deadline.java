package tasks;

/**
 * Represents Deadline task
 */
public class Deadline extends Task {

    private String by;

    /**
     *
     * @param name
     * @param isDone
     * @param by deadline date (dd-MM-yyyy HH:mm)
     */
    public Deadline(String name, Boolean isDone, String by) {
        super(name, isDone);
        this.by = by;
    }

    /**
     * @return String representation of the Deadline type
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     *
     * @return String representation of the deadline for the CLI output
     */
    public String toString() {
        return "[D]" + super.toString() + " (" + "by: " + by + ")";
    }

    /**
     *
     * @return String representation of the deadline for the file output
     */
    @Override
    public String getFileFormatString() {
        return String.format("%s | %d | %s | %s",
                this.getType(),
                this.isDone ? 1 : 0,
                this.name,
                this.by
        );
    }
}
