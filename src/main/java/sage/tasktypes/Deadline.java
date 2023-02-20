package sage.tasktypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * A class that represents a deadline task
 */

public class Deadline extends Task {
    private String byWhen = "";

    private LocalDate date = null;

    public Deadline(String taskName, String byWhen) {
        super(taskName);
        DateTimeFormatter dateformat1 = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        DateTimeFormatter dateformat2 = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        try {
            this.date = LocalDate.parse(byWhen, dateformat1);
        } catch (DateTimeParseException e1) {
            try {
                this.date = LocalDate.parse(byWhen, dateformat2);
            } catch (DateTimeParseException e2) {
                this.byWhen = byWhen;
            }
        }
    }

    public String getByWhen() {
        return byWhen;
    }

    public String getFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return this.date.format(formatter);
    }

    @Override
    public String toString() {
        if (this.date != null) {
            if (super.isCompleted()) {
                return "[D][X] " + super.getTaskDetails() + "(by: " + getFormattedTime() + ")";
            } else {
                return "[D][ ] " + super.getTaskDetails() + "(by: " + getFormattedTime() + ")";
            }
        } else {
            if (super.isCompleted()) {
                return "[D][X] " + super.getTaskDetails() + "(by: " + getByWhen() + ")";
            } else {
                return "[D][ ] " + super.getTaskDetails() + "(by: " + getByWhen() + ")";
            }
        }
    }
}
