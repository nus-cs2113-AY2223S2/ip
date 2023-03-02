package Duke;

/**
 * Abstract class that represents each task set by user
 * Keeps track of the task information, as well as the task's current status.
 */
public abstract class Task {
    private String content;
    private boolean isCompleted;

    Task(String content) {
        this.content = content;
        this.isCompleted = false;
    }

    /**
     * Returns task's completion status.
     *
     * @return Completion status of task.
     */
    boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Changes task's completion status to complete.
     *
     * @return Complete verison of the current task.
     */
    Task markAsComplete() {
        this.isCompleted = true;
        return this;
    }
    /**
     * Changes task's completion status to incomplete.
     *
     * @return Incomplete verison of the current task.
     */
    Task markAsIncomplete() {
        this.isCompleted = false;
        return this;
    }

    abstract String getType();

    public String toString() {
        return (this.isCompleted ? "[X] " : "[ ] ") + this.content;
    }
}
