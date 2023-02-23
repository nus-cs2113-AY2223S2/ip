package duke.task;

import duke.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    public static final String DEADLINE_LABEL = "D";
    protected LocalDateTime by;
    protected String byString;

    /**
     * Initialises as in Task, with added parsing for due date.
     * If parsing is not possible, save due date as String.
     *
     * @param description String describing the Task
     * @param by String describing the due date
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by);
        } catch (DateTimeParseException e) {
            this.byString = by;
        }
    }

    /**
     * Get a String describing the due date of the Deadline.
     *
     * @param pattern Desired format for String after parsing
     * @return Parsed due date
     */
    public String getBy(DateTimeFormatter pattern) {
        return Parser.parseDateTime(by, byString, pattern);
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public Boolean isOnDate(LocalDate date) {
        if (by != null && date.isEqual(by.toLocalDate())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + DEADLINE_LABEL + "][" + getStatus() + "] " + description + " (by: " + getBy(printPattern) + ")";
    }

}
