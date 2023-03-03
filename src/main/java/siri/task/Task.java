package siri.task;

/**
 * Represents a Task.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task with a description.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setting the task as done or not done.
     * @param done
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * @return the string that represents Done and Not done in the task list.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * @return the string that represents Done and Not done in the task list in file.
     */
    public String getFileStatusIcon(){
        return (isDone? "X" : "0");
    }

    public String toFileString() {
        return getFileStatusIcon() + " | " + description;
    }
}