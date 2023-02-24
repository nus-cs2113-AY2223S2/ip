package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toFileFormat() {
        return "D | " + isMarked + " | " + description + " | " + deadline + "\n";
    }

    @Override
    public String toString() {
        try {
            String formattedDeadline;
            formattedDeadline = LocalDate.parse(deadline).format(DateTimeFormatter.ofPattern("dd MMM uuuu"));
            return "[D]" + super.getStatusIcon() + " " + super.description + "(by: " + formattedDeadline + ")";
        } catch (DateTimeException e) {
            return "[D]" + super.getStatusIcon() + " " + super.description + "(by: " + deadline + ")";
        }
    }
}
