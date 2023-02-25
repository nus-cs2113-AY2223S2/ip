package duke.task;

/**
 * Represents a general task. All task objects have a description, a status for if it has been done
 * and a specific type of task
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Constructor for a general task
     *
     * @param description the description of the current task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the description of the current task
     *
     * @return description of the specified task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the status of the task
     *
     * @return True if it is marked as done, False otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the status of the task
     *
     * @return "X" if it is done and " " if it is not done
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Marks the current task as done
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark the current task as done
     */
    public void setAsUndone() {
        this.isDone = false;
    }

    /**
     * Get the specified type of current task
     *
     * @return the type of the current task
     */
    public String getType() {
        return type;
    }

    public String toString() {
        return "";
    }
}
