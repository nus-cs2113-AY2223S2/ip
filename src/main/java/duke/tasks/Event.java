package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.main.Duke.taskCount;

public class Event extends Task {
    private LocalDateTime startTime, endTime;


    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStart() {
        return this.formatStartTime();
    }

    public String getEnd() {
        return this.formatEndTime();
    }

    public String formatStartTime() {
        return this.startTime.format(dateTimeFormatter);
    }

    public String formatEndTime() {
        return this.endTime.format(dateTimeFormatter);
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.formatStartTime() + " to: " + this.formatEndTime() + ")";
    }
}
