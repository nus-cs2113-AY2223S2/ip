package duke.task;

public class Task {
    public String description;
    public boolean isDone;

    /**
     * Constructor to initialise description and task completion status.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns string "X" if the task is completed else returns space.
     *
     * @return Task Completion Status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Returns the task completion status in the required format.
     *
     * @return Task completion Status in the required String format.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

