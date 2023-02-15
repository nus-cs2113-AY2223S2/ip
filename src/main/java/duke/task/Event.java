package duke.task;

import java.time.LocalDateTime;

import duke.utils.Output;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String desciption, LocalDateTime from, LocalDateTime to) {
        super(desciption);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (from: " + Output.dateFormatter(from) + " to: " + Output.dateFormatter(to) + ")";
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    public LocalDateTime getStartTime() {
        return from;
    }

    public LocalDateTime getEndTime() {
        return to;
    }
}
