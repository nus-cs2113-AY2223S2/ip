package duke.tasks;

import duke.parser.DateTimeParser;

import java.time.LocalDateTime;

public class Event extends Task {
    public static final String MARKER = "E";
    private final LocalDateTime from, to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskEnum.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String describe() {
        return getCheckbox(true, MARKER)
                + super.describe()
                + " (from: " + from.format(DateTimeParser.getFormatter())
                + " to: " + to.format(DateTimeParser.getFormatter())
                + ")";
    }
}
