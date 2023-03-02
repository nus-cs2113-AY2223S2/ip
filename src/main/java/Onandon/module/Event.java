package Onandon.module;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime to;
    protected LocalDateTime from;
    protected String dateFormat = "dd,MM,yyyy HHmm";

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
        String printTo = to.format(DateTimeFormatter.ofPattern(dateFormat));
        String printFrom = from.format(DateTimeFormatter.ofPattern(dateFormat));
        return "[E]" + super.toString() + " " + description + " (from: " + printFrom + " to: " + printTo + ")";
    }
}