package tasks;

public abstract class Task {
    /**
     * The description and marker of the task
     */
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the given description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIsDone(boolean done) {
        this.isDone = done;
    }
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * This <code>getType()</code> method will be overridden in the 3 different type classes
     * Returns the type of the task.
     *
     * @return null
     */
    public String getType() {
        return null;
    }

    /**
     * This <code>toString()</code> method will be overridden in the 3 different type classes
     * Returns a string representation of the task, including status and description.
     *
     * @return a string representation of the task
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}