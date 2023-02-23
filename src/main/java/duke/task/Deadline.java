package duke.task;

import duke.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    public static final String DEADLINE_LABEL = "D";
    protected LocalDateTime by;
    protected String byString;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by);
        } catch (DateTimeParseException e) {
            this.byString = by;
        }
    }

    public String getType() {
        return "deadline";
    }

    public String getBy(String pattern) {
        return Parser.parseDateTime(by, byString, pattern);
    }

    @Override
    public String toString() {
        return "[" + DEADLINE_LABEL + "][" + getStatus() + "] " + description + " (by: " + getBy(printPattern) + ")";
    }
}
