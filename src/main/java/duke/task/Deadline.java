package duke.task;

public class Deadline extends Task {
    protected String dueBy;

    public Deadline(String task, String dueBy) {
        super(task);
        this.dueBy = dueBy;
        this.type = "Deadline";
    }

    /**
     * gets the due date of the task
     *
     * @return formatted string of due date
     */
    public String getDueDate() {
        return "(by:" + dueBy + ")";
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
        return "[D]" + "[" + getStatusIcon() + "] " + task + getDueDate();
    }
}
