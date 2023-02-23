package duke.tasks;

import java.time.LocalDateTime;

/**
 * One of the three task type (todo, deadline, event)
 */
public class Event extends Task {
    private LocalDateTime startTime, endTime;


    /**
     * @param description task name
     * @param startTime
     * @param endTime
     */

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
