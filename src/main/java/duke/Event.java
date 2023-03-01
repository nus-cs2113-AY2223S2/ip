package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        //return "[E]" + super.toString() + " (from: " + by + ")";
        LocalDate startDate = LocalDate.parse(start.trim());
        LocalDate endDate = LocalDate.parse(end.trim());
        return "[E]" + super.toString() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to " + endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

