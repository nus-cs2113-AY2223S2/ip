import duke.Task;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents a deadline task.
 * It is a child of the Task class.
 */
public class Deadline extends Task{
    public String by;
    public String by_copy;

    /**
     * Creates a deadline task with the given description and deadline.
     * @param description The description of the deadline task.
     * @param by The deadline of the deadline task.
     */
    public Deadline (String description, String by) {
        super(description);
        by_copy = by;
        LocalDateTime date = LocalDateTime.parse(by);
        this.by = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Returns the string representation of the deadline task.
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
