package duke;

/**
 * Represents a Deadline task type. A <code>Deadline</code> object corresponds
 * to a <code>Task</code> object that requires an additional argument which is
 * the cutoff date/time. This is indicated by the user via the <code>/by</code>
 * command e.g., <code>/deadline do homework /by sunday evening</code>
 */
public class Deadline extends Task {
    protected String cutoffDate;

    /**
     * Initialises the superclass <code>Task</code> and sets the description and
     * cutoff.
     * 
     * @param arguments user input containing task description and cutoff.
     */
    public Deadline(String arguments) {
        super();
        String[] argumentsArray = arguments.split("/by", 2);
        String deadlineDescription = argumentsArray[0].trim();
        String deadlineCutoff = argumentsArray[1].trim();
        this.cutoffDate = deadlineCutoff;
        super.description = deadlineDescription;
    }

    @Override
    public String formattedString() {
        String formatted = "Deadline:" + super.isDone + ":" + super.description + ":" + cutoffDate;
        return formatted;
    }

    @Override
    public String toString() {
        return "[DEADLINE]\n" + super.toString() + " (By: " + cutoffDate + ")";
    }

}
