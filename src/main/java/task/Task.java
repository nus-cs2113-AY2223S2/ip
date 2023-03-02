package task;

/**
 * Represent a Task.
 */
public abstract class Task {
    protected String description;

    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the icon representing the task's status
     *
     * @return "X" if task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Get the number representing the task's status
     *
     * @return "1" if task is done, "0" otherwise.
     */
    public String getStatusNum() {
        return (isDone ? "1" : "0");
    }

    /**
     * Mark the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Get the description of the task.
     *
     * @return The description of the task as a string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the character representing the task's type.
     *
     * @return "T" if task is Todo, "E" if task if Event, and "D" if task is Deadline.
     */
    public abstract String getTaskType();

    /**
     * Get the summary of the task in the format used to store in duke.txt.
     *
     * @return the summary of the task in the format used to store in duke.txt.
     */
    public String getDataSummary() {
        return getTaskType() + " | " + getStatusNum() + " | " + getDescription();
    }

    /**
     * Get the summary of the task in the format used to display to the user.
     *
     * @return the summary of the task in the format used to display to the user.
     */
    public String getSummary() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + getDescription();
    }
}