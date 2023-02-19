import duke.Task;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Deadline extends Task{
    public String by;
    public Deadline (String description, String by) {
        super(description);
        LocalDateTime date = LocalDateTime.parse(by);
        this.by = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
