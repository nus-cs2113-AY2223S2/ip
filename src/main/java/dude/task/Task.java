package dude.task;


public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for Task
     * 
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task
     * 
     * @return Description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task
     * 
     * @return Status of the task X if done, space if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the status of the task
     * @return Status of the task 1 if done, 0 if not done
     */
    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    /**
     * Marks the task as done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task
     * 
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation of the task to be saved
     * 
     * @return String representation of the task to be saved
     */
    public String toSave() {
        return "";
    }

}
