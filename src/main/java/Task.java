public class Task {
    /** The description of the task entered by the user. */
    protected String description;
    /** The state of the task, whether it is done or not. */
    protected boolean isDone;
    /** The index of the task in the list */
    protected int index;

    public Task(String description, int index) {
        this.description = description;
        this.index = index;
        this.isDone = false;
    }

    /**
     * Returns the symbol to be printed depends on whether the task is marked as done or not.
     * @return The symbol whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the state of the task: whether is it done or not.
     * @return isDone The state of the task.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Toggles the state of the task between done and not done.
     */
    public void switchIsDone() {
        isDone = !isDone;
    }

    /**
     * Returns the description of the task that the user entered.
     * @return description The description of the task.
     */
    public String getDescription() {
        return description;
    }
}

