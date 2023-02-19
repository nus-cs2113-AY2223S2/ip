import duke.Task;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
    * Represents an event task.
 */
public class Event extends Task{
    protected String from;
    protected String to;
    protected String from_copy;
    protected String to_copy;

    /**
     * Creates an event task with the given description and time period.
     * @param description The description of the event task.
     * @param from The start time of the event task.
     * @param to The end time of the event task.
     */
    public Event (String description, String from, String to) {
        super(description);
        from_copy = from;
        to_copy = to;
        LocalDateTime startDate = LocalDateTime.parse(from);
        LocalDateTime endDate = LocalDateTime.parse(to);
        this.from = startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        this.to = endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Returns the string representation of the event task.
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
