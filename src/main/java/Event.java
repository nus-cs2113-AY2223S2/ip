import duke.Task;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class Event extends Task{
    protected String from;
    protected String to;
    public Event (String description, String from, String to) {
        super(description);
        LocalDateTime startDate = LocalDateTime.parse(from);
        LocalDateTime endDate = LocalDateTime.parse(to);
        this.from = startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        this.to = endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
