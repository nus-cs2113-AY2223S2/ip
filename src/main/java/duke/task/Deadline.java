package duke.task;

import java.time.LocalDateTime;

/**
 * Task with a deadline
 */
public class Deadline extends Task {
    protected String dueBy;

    /**
     * Constructor to set a deadline when with no given datetime
     * sets default to current datetime
     *
     * @param task  description of the deadline
     * @param dueBy due description
     */
    public Deadline(String task, String dueBy) {
        super(task);
        this.dueBy = dueBy;
        this.type = "Deadline";
        this.endTime = LocalDateTime.now();
    }

    /**
     * Constructor to set a deadline with given datetime
     *
     * @param task    description of deadline
     * @param dueBy   due description
     * @param endTime time of the due date
     */
    public Deadline(String task, String dueBy, LocalDateTime endTime) {
        super(task);
        this.dueBy = dueBy;
        this.type = "Deadline";
        this.endTime = endTime;
    }

    /**
     * gets the due description of the deadline
     *
     * @return formatted string of due description
     */
    public String getDueBy() {
        return "(by: " + dueBy + ")";
    }

    /**
     * gets the end time of deadline
     *
     * @return LocalDateTime of dueDate
     */
    @Override
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * gets the type of task (deadline type)
     *
     * @return letter representing type of task
     */
    @Override
    public String getTaskType() {
        return "[D]";
    }

    /**
     * Shows the full deadline status and description
     *
     * @return deadline status and description
     */
    @Override
    public String getTaskStatus() {
        return "[D]" + "[" + getStatusIcon() + "] " + task + getDueBy();
    }

}
