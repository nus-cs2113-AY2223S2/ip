package duke.task;

import duke.ui.TextUi;

/**
 * Represents a task in the task list.
 */
public abstract class Task {
    private boolean isDone;
    private String desciption;

    protected TextUi ui = new TextUi();

    /**
     * Constructs a task object with the given description and sets its completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String desciption) {
        this.desciption = desciption;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getTaskDescription() {
        return desciption;
    }

    /**
     * Returns the status of the task.
     *
     * @return The status of the task.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns the status icon of the task, which is "X" for marked tasks and " " for unmarked ones.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the completion status of the task to the given status.
     *
     * @param status The completion status of the task.
     */
    public void setStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getTaskDescription();
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public abstract String getTaskType();
}
