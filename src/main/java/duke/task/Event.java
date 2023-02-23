package duke.task;

import duke.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public static final String EVENT_LABEL = "E";
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected String fromString;
    protected String toString;

    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDateTime.parse(from);
        } catch (DateTimeParseException e) {
            this.fromString = from;
        }
        try {
            this.to = LocalDateTime.parse(to);
        } catch (DateTimeParseException e) {
            this.toString = to;
        }
    }

    public String getType() {
        return "event";
    }

    public String getFrom(DateTimeFormatter pattern) {
        return Parser.parseDateTime(from, fromString, pattern);
    }

    public String getTo(DateTimeFormatter pattern) {
        return Parser.parseDateTime(to, toString, pattern);
    }

    @Override
    public String toString() {
        return "[" + EVENT_LABEL + "][" + getStatus() + "] " + description +
                " (from: " + getFrom(printPattern) + " to: " + getTo(printPattern) + ")";
    }
}
