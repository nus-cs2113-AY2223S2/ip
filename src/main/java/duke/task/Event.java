package duke.task;

import java.time.LocalDateTime;

/**
 * A class representing a Event task.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description The description of the Event task.
     * @param startDate   The start date of the Event task.
     * @param endDate     The end date of the Event task.
     */
    public Event(String desciption, LocalDateTime startTime, LocalDateTime endTime) {
        super(desciption);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString()
                + " (from: " + ui.getShowDateFormat(startTime)
                + " to: " + ui.getShowDateFormat(endTime) + ")";
    }

    /**
     * Returns the task type, "E" for event.
     *
     * @return the task type
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns the start date of the task.
     *
     * @return the start date of the task
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end date of the task.
     *
     * @return the end date of the task
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }
}
