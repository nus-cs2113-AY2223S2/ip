package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    protected String startString;
    protected String endString;
    protected LocalDateTime startTime;

    public Event(String task, String startString, String endString) {
        super(task);
        this.startString = startString;
        this.endString = endString;
        this.type = "Event";
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now();
    }

    public Event(String task, String startString, String endString, LocalDateTime startTime, LocalDateTime endTime) {
        super(task);
        this.startString = startString;
        this.endString = endString;
        this.type = "Event";
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * gets the duration of the event
     *
     * @return the formatted string of the event duration
     */
    public String getDuration() {
        return " (from: " + startString + " to: " + endString + ")";
    }

    @Override
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * gets the type of task (event type)
     *
     * @return letter representing type of task
     */
    @Override
    public String getTaskType() {
        return "[E]";
    }

    /**
     * Shows the full event status and description
     *
     * @return event status and description
     */
    @Override
    public String getTaskStatus() {
        return "[E]" + "[" + getStatusIcon() + "] " + task + getDuration();
    }
}
