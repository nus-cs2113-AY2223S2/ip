package duke.tasks;

import duke.constants.Config;
import duke.parser.datetime.DateTimeParser;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime fromDateTime, toDateTime;

    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description, TaskEnum.EVENT);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Describes the task with its task type, status, description, event start and end details.
     *
     * @return String describing the task
     */
    @Override
    public String describeTask() {
        return getCheckbox(true, Config.MARKER_EVENT)
                + super.describeTask()
                + " (from: " + fromDateTime.format(DateTimeParser.getFormatter())
                + " to: " + toDateTime.format(DateTimeParser.getFormatter())
                + ")";
    }
}
