package Onandon.module;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


// Class for the 'event' command.
public class Event extends Task {

    protected LocalDateTime to;
    protected LocalDateTime from;
    protected String dateFormat = "yyyy-MM-dd";
    protected String printDateFormat = "MMM dd yyyy";

    public Event(String description, String to, String from) {
        super(description);
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern(dateFormat));
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern(dateFormat));
    }

    @Override
    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern(dateFormat));
    }

    @Override
    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern(dateFormat));
    }

    @Override
    public String toString() {
        String printTo = to.format(DateTimeFormatter.ofPattern(printDateFormat));
        String printFrom = from.format(DateTimeFormatter.ofPattern(printDateFormat));
        return "[E]" + super.toString() + " " + description + " (from: " + printFrom + " to: " + printTo + ")";
    }
}