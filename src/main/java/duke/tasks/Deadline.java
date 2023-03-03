package duke.tasks;

import duke.constants.Config;
import duke.parser.datetime.DateTimeParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime byDateTime;

    public Deadline(String description, LocalDateTime byDateTime) {
        super(description, TaskEnum.DEADLINE);
        this.byDateTime = byDateTime;
    }

    /**
     * Describes the task with its task type, status, description and completion deadline.
     *
     * @return String describing the task
     */
    @Override
    public String describeTask() {
        return getCheckbox(true, Config.MARKER_DEADLINE)
                + super.describeTask()
                + " (by: " + byDateTime.format(DateTimeParser.getFormatter())
                + ")";
    }
}
