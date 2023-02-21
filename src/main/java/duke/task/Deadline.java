package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    protected String dueBy;

    public Deadline(String task, String dueBy) {
        super(task);
        this.dueBy = dueBy;
        this.type = "Deadline";
        this.endTime = LocalDateTime.now();
    }

    public Deadline(String task, String dueBy, LocalDateTime endTime) {
        super(task);
        this.dueBy = dueBy;
        this.type = "Deadline";
        this.endTime = endTime;
    }

    /**
     * gets the due string of the task
     *
     * @return formatted string of due date
     */
    public String getDueBy() {
        return "(by: " + dueBy + ")";
    }

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
