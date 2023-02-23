package inu.task;

/**
 * Represents a task.
 */
public abstract class Task {

    private String description;

    private boolean isDone;

    /**
     * Constructor for task.
     *
     * @param description description of the task
     */
    public Task(String description) {
        setDescription(description);
        resetDone();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set status of task to done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Set status of task to not done.
     */
    public void resetDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), getDescription());
    }

    /**
     * Converts task to string to be saved in storage file.
     *
     * @return string to save in file.
     */
    public String encodeTaskToString() {
        return "";
    }

}
