package duke.task;

import java.time.LocalDateTime;

/**
 * A class representing a Deadline task.
 */
public class Deadline extends Task {

    private LocalDateTime endDate;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param endDate     The deadline of the Deadline task.
     */
    public Deadline(String description, LocalDateTime endDate) {
        super(description);
        this.endDate = endDate;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString()
                + " (by: " + ui.getShowDateFormat(endDate) + ")";
    }

    /**
     * Returns the task type, "D" for deadline.
     *
     * @return the task type
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns the end date of the task.
     *
     * @return the end date of the task
     */
    public LocalDateTime getEndTime() {
        return endDate;
    }
}
