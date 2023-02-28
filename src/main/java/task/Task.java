package task;

/**
 * Handles the Tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor that accepts a description.
     * isDone is set to false by default when a task is created.
     *
     * @param description The description to accept.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter for the description.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the status icon.
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Setter for the description.
     *
     * @param description set the description to the specified description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set the task to done or not done.
     *
     * @param done the boolean to set the task as done or not done.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        setDone(true);
        setDescription(description.replace("[ ]", "[X]"));
    }

    /**
     * Mark the task as not done.
     */
    public void markAsUndone() {
        setDone(false);
        setDescription(description.replace("[X]", "[ ]"));
    }

    /**
     * Set the toString parameter to print out a readable string.
     *
     * @return The readable string.
     */
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }

    /**
     * Formats the string into a savable string.
     *
     * @return A string that can be saved into a file.
     */
    public String savableString() {
        String status;
        if (getStatusIcon().equals(" ")) {
            status = "0";
        } else {
            status = "1";
        }
        return status + "|" + description;
    }
}
