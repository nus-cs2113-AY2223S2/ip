package duke.task;

import duke.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public static final String EVENT_LABEL = "E";
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected String fromString;
    protected String toString;

    /**
     * Initialises as in Task, with added parsing for start and end dates.
     * If parsing is not possible, save date(s) as String(s).
     *
     * @param description String describing the Task
     * @param from String describing the start date
     * @param to String describing the end date
     */
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

    /**
     * Get a String describing the start date of the Event.
     *
     * @param pattern Desired format for String after parsing
     * @return Parsed start date
     */
    public String getFrom(DateTimeFormatter pattern) {
        return Parser.parseDateTime(from, fromString, pattern);
    }

    /**
     * Get a String describing the end date of the Event.
     *
     * @param pattern Desired format for String after parsing
     * @return Parsed end date
     */
    public String getTo(DateTimeFormatter pattern) {
        return Parser.parseDateTime(to, toString, pattern);
    }

    @Override
    public String getType() {
        return "event";
    }

    @Override
    public Boolean isOnDate(LocalDate date) {
        if (from != null) {
            if (date.isEqual(from.toLocalDate())) {
                return true;
            }
            if (date.isBefore(from.toLocalDate())) {
                return false;
            }
            if (to != null && date.isBefore(to.toLocalDate())) {
                return true;
            }
        }
        if (to != null && date.isEqual(to.toLocalDate())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + EVENT_LABEL + "][" + getStatus() + "] " + description +
                " (from: " + getFrom(printPattern) + " to: " + getTo(printPattern) + ")";
    }
}
