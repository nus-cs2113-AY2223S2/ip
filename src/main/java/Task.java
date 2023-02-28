public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /** Creates a task, which must contain a description */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Returns whether a task is complete or incomplete */
    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    /** Marks a task as done */
    public void markAsDone(){
        isDone = true;
    }

    /** Marks a task as not done */
    public void unmarkAsDone(){
        isDone = false;
    }

    /** Returns type of task */
    public String getType() {
        return null;
    }

    /** Returns relevant timings of task, if any */
    public String getTimings() {
        return null;
    }
}
