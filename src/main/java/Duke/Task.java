package Duke;

/**
 * Abstract class that represents each task set by user
 * Keeps track of the task information, as well as the task's current status.
 */
public abstract class Task {
    protected String content;
    private boolean isCompleted;

    public Task(String content) {
        this.content = content;
        this.isCompleted = false;
    }

    /**
     * Returns task's completion status.
     *
     * @return Completion status of task.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Changes task's completion status to complete.
     *
     * @return Complete verison of the current task.
     */
    public Task markAsComplete() {
        this.isCompleted = true;
        return this;
    }
    /**
     * Changes task's completion status to incomplete.
     *
     * @return Incomplete verison of the current task.
     */
    public Task markAsIncomplete() {
        this.isCompleted = false;
        return this;
    }

    /**
     * Returns boolean on whether the keyword in contained in the task's information.
     * It looks through the event's information to determine keyword's relevance.
     * The search criteria is implemented specific to the task subtype.
     *
     * @param keyword Word that is looked for in the task.
     * @return Boolean on whether the task contains the keyword.
     */
    public abstract boolean contains(String keyword);

    public abstract String getType();

    public String toString() {
        return (this.isCompleted ? "[X] " : "[ ] ") + this.content;
    }
}
