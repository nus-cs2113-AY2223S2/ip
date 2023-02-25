package duke.task;

import duke.Parser;
import duke.exception.DateOrderException;

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
     * @param fromString String describing the start date
     * @param toString String describing the end date
     * @throws DateOrderException If the end date occurs before the start date
     */
    public Event(String description, String fromString, String toString) throws DateOrderException {
        super(description);
        try {
            this.from = LocalDateTime.parse(fromString);
        } catch (DateTimeParseException e) {
            this.fromString = fromString;
        }
        try {
            this.to = LocalDateTime.parse(toString);
        } catch (DateTimeParseException e) {
            this.toString = toString;
        }
        if (this.from != null && this.to != null) {
            if (from.isAfter(to)) {
                throw new DateOrderException();
            }
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
        boolean fromExists = (from != null);
        boolean toExists = (to != null);
        LocalDate fromDate = null;
        LocalDate toDate = null;
        if (fromExists) {
            fromDate = from.toLocalDate();
        }
        if (toExists) {
            toDate = to.toLocalDate();
        }
        boolean isOnFrom = fromExists && date.isEqual(fromDate);
        boolean isOnTo = toExists && date.isEqual(toDate);
        boolean isBetween = fromExists && toExists && date.isAfter(fromDate) && date.isBefore(toDate);

        return isOnFrom || isOnTo || isBetween;
    }

    @Override
    public String toString() {
        return "[" + EVENT_LABEL + "][" + getStatus() + "] " + description +
                " (from: " + getFrom(printPattern) + " to: " + getTo(printPattern) + ")";
    }
}
