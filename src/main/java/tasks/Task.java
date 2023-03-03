package tasks;

public class Task {

    protected String type;
    protected String description;
    protected boolean isDone;

    public String getType() { return type; }
    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Class constructor with <code>description</code> and
     * <code>type</code> parameters for initialization.
     *
     * @param description the description of the task.
     * @param type the category of task.
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getStatusFileIcon() { return (isDone ? "1" : "0"); }

    public String toString() {
        return '[' + getStatusIcon() + "] "+ description + " ";
    }
}
