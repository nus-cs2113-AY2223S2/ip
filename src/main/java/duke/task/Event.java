package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String desciption, LocalDateTime startTime, LocalDateTime endTime) {
        super(desciption);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString()
                + " (from: " + ui.getShowDateFormat(startTime)
                + " to: " + ui.getShowDateFormat(endTime) + ")";
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
