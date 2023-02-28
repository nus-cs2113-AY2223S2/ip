package duke;

/**
 * Represents a task that the user has to do.
 */
public class Task {
    public String description;
    public boolean isDone;

    /**
     * Creates a task with the given description.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task. If the task is done, the status icon is an X.
     * Otherwise, it is a space.
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }
    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
