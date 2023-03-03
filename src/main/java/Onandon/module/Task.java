package Onandon.module;
import java.time.LocalDate;

/**
 * Representation of the task class
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Create new task class with the specified configuration.
     *
     * @param description Description of the command.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return 'isDone' parameter so that user can know whether this class is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Return 'description' parameter so that user can check description of the class.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set 'isDone' to 'true' to mark this class as 'done'.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Set 'isDone' to 'false' to mark this class as 'not done'.
     */
    public void markAsUnDone() {
        isDone = false;
    }
    public String getBy() { return ""; }
    public String getTo() { return ""; }
    public String getFrom() { return ""; }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]";
    }
}