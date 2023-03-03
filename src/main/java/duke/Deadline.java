package duke;

/**
 * Deadline subclass of Task
 */
public class Deadline extends Task {
    String by;

    /**
     * Constructor for a Deadline object
     * @param description description of the Deadline
     * @param by string of the "do by" date
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = TaskType.DEADLINE;
    }

    /**
     * Gets a formatted string of the Deadline along with its properties
     * @return the formatted string
     */
    @Override
    public String getLabel() {
        String typeIndicator = "[D]";
        String doneIndicator = "[" + (this.isDone ? "X" : " ") + "]";
        String suffix = "(by: " + this.by + ")";
        return typeIndicator + doneIndicator + " " + this.description + " " + suffix;
    }
}
