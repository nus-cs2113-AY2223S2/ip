package duke.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.zip.DataFormatException;

import static duke.main.Duke.taskCount;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) throws DataFormatException {
        super(description);
        this.deadline = deadline;
    }

    public String formatDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy ha "));
    }

    public String getDeadline() {
        return this.formatDeadline();
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatDeadline() + ")";
    }


}
