package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon for the task, either [X] if completed or [ ] if not.
     *
     * @return Status Icon.
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
