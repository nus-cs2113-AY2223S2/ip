package duke.task;

import java.time.LocalDateTime;

/**
 * Task with a start and end date.
 */
public class Event extends Task {
    protected String startString;
    protected String endString;
    protected LocalDateTime startTime;

    /**
     * Constructs an event without a given datetime
     * defaults to current time.
     *
     * @param task        Description of event.
     * @param startString Starting duration description.
     * @param endString   Ending duration description.
     */
    public Event(String task, String startString, String endString) {
        super(task);
        this.startString = startString;
        this.endString = endString;
        this.type = "Event";
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now();
    }

    /**
     * Constructs an event with given datetime.
     *
     * @param task        Description of event.
     * @param startString Starting duration description.
     * @param endString   Ending duration description.
     * @param startTime   Datetime of start date.
     * @param endTime     Datetime of the due date.
     */
    public Event(String task, String startString, String endString, LocalDateTime startTime, LocalDateTime endTime) {
        super(task);
        this.startString = startString;
        this.endString = endString;
        this.type = "Event";
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets the duration of the event.
     *
     * @return The formatted string of the event duration.
     */
    public String getDuration() {
        return " (from: " + startString + " to: " + endString + ")";
    }

    /**
     * Gets the end time of event.
     *
     * @return LocalDateTime of dueDate.
     */
    @Override
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Gets the type of task (event type).
     *
     * @return Letter representing type of task.
     */
    @Override
    public String getTaskType() {
        return "[E]";
    }

    /**
     * Shows the full event status and description.
     *
     * @return Event status and description.
     */
    @Override
    public String getTaskStatus() {
        return "[E]" + "[" + getStatusIcon() + "] " + task + getDuration();
    }

}
