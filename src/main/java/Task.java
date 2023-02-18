/**
 * Represents a task which can be added to the list of tracked tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task, whether it is done or not.
     * @return "X" when task is done, " " when task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the task status depending on the isDone parameter. When isDone is
     * true, task is set to done. Otherwise, task is set to not done.
     * @param isDone The status which the user wants to set the task to
     */
    public void setTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the type of the current task.
     * @return "T", "D" or "E" depending on current task type
     */
    public String getTaskType() {
        return "";
    }

    /**
     * Returns the deadline of Deadline tasks.
     * @return Deadline
     */
    public String getBy() {
        return "";
    }

    /**
     * Returns the start date/time of Events tasks.
     * @return Start date/time
     */
    public String getFrom() {
        return "";
    }

    /**
     * Returns the end date/time of Event tasks.
     * @return End date/time
     */
    public String getTo() {
        return "";
    }

    /**
     * Returns the status and description of the current task.
     * @return A string containing task's status and description
     */
    @Override
    public String toString() {
        String icon = getStatusIcon();
        String output;
        output = "[" + icon + "] " + description;
        return output;
    }
}
