package duke.tasks;

import duke.parser.DateTimeParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    public static final String MARKER = "D";
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, TaskEnum.DEADLINE);
        this.by = by;
    }

    @Override
    public String describe() {
        return getCheckbox(true, MARKER)
                + super.describe()
                + " (by: " + by.format(DateTimeParser.getFormatter())
                + ")";
    }
}
