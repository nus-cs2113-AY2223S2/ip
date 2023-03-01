package duke.task;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;

import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;

public class Deadline extends Task {
    // protected String by;
    protected LocalDateTime by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = LocalDateTime.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(ofLocalizedDateTime(FormatStyle.MEDIUM)) + ")";
    }
    // need to format the "by" now.
}
