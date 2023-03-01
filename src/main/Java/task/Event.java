package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getRawStart() {
        return start;
    }

    public LocalDateTime getRawEnd() {
        return end;
    }

    public String getStart() {
        String literal = nthNumber(Integer.parseInt(start.format(DateTimeFormatter.ofPattern("d"))));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(String.format("d 'of' MMMM yyyy', 'h:ma"));
        String formatted = start.format(formatter);
        String date = formatted.split(" ")[0];
        String rest = formatted.substring(formatted.indexOf(" "));
        String newFormatted = date + literal + rest;
        return newFormatted;
    }

    public String getEnd() {
        String literal = nthNumber(Integer.parseInt(end.format(DateTimeFormatter.ofPattern("d"))));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(String.format("d 'of' MMMM yyyy', 'h:ma"));
        String formatted = end.format(formatter);
        String date = formatted.split(" ")[0];
        String rest = formatted.substring(formatted.indexOf(" "));
        String newFormatted = date + literal + rest;
        return newFormatted;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + " (from: " + getStart() +
                " to: " + getEnd() + ")";
    }
}
