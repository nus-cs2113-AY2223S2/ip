package duke;

/**
 * Abstract class representing a Task to be completed. Extended by Todo, Deadline, and Event classes.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes Task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon for the task, either [X] if completed or [ ] if not.
     *
     * @return String representation of status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks a task as completed.
     *
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not completed.
     *
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return Task Description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns standardized string to be stored on memory that can be read back into the program in the future.
     *
     * @return Standardized string to be stored in memory.
     */
    public String storeString() {
        return (isDone ? "1" : "0");
    }

    @Override
    /**
     * Returns string representation of the task, with status icon and description.
     *
     * @return String Representation.
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
