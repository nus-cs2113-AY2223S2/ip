package duke.task;

import java.time.LocalDateTime;

/**
 * Task with a deadline.
 */
public class Deadline extends Task {
    protected String dueBy;

    private static final String TASK_ICON = "[D]";
    private static final String TASK_TYPE = "Deadline";

    /**
     * Constructs a deadline when with no given datetime
     * sets default to current datetime.
     *
     * @param task  Description of the deadline.
     * @param dueBy Due description.
     */
    public Deadline(String task, String dueBy) {
        super(task);
        this.dueBy = dueBy;
        this.type = TASK_TYPE;
        this.endTime = LocalDateTime.now();
    }

    /**
     * Constructs a deadline with given datetime.
     *
     * @param task    Description of deadline.
     * @param dueBy   Due description.
     * @param endTime Time of the due date.
     */
    public Deadline(String task, String dueBy, LocalDateTime endTime) {
        super(task);
        this.dueBy = dueBy;
        this.type = TASK_TYPE;
        this.endTime = endTime;
    }

    /**
     * Gets the due description of the deadline.
     *
     * @return Formatted string of due description.
     */
    public String getDueBy() {
        return "(by: " + dueBy + ")";
    }

    /**
     * Gets the end time of deadline.
     *
     * @return LocalDateTime of dueDate.
     */
    @Override
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Gets the type of task (deadline type).
     *
     * @return Letter representing type of task.
     */
    @Override
    public String getTaskType() {
        return TASK_ICON;
    }

    /**
     * Shows the full deadline status and description.
     *
     * @return Deadline status and description.
     */
    @Override
    public String getTaskStatus() {
        return TASK_ICON + "[" + getStatusIcon() + "] " + task + getDueBy();
    }

}
