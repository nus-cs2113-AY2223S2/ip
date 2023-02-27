package util;

/**
 * This class represents a Task object, which contains a description and a
 * status indicator of whether the task is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task object that takes in a description and initializes the
     * isDone status to false.
     * 
     * @param description a String that represents the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {
        this("");
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * returns a String representation of the task that can be used for saving to a
     * file.
     * 
     * @return a String that is formatted with the appropriate symbols and data for
     *         saving to a file
     */
    public String toStringForSave() {
        String doneStatus = isDone() ? "1" : "0";
        return String.format("T | %s | %s", doneStatus, getDescription());
    }
}
