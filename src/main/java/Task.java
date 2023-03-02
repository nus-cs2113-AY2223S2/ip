public class Task {
    public final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return the Icon of current task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark the task to done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task to unDone
     */
    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * @return convert to formatted string
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
