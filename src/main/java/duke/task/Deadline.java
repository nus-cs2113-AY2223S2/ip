package duke.task;

import java.time.LocalDateTime;

import duke.utils.Output;

public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (by: " + Output.dateFormatter(by) + ")";
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    public LocalDateTime getEndTime() {
        return by;
    }
}
