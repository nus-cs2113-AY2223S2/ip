package duke.tasks;

/**
 * Parent class for task objects. Other subclasses inherit from this class.
 */
public class Task {

    /**
     * String that provides the description of the task.
     */
    protected String description;

    /**
     * Boolean value on whether the task is marked as done or not.
     */
    protected boolean isDone;

    /**
     * Constructor of the task object which initialises its description and
     * set it as undone.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon to represent whether the task has been marked.
     *
     * @return String icon that represents marked or unmarked.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //...

    /**
     * Sets the task completion status.
     *
     * @param done boolean to determine whether task should be marked as completed.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Converts the task into its string representation which contains
     * information such as its status icon and its description.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts the task into a format to be saved in the database.
     *
     * @return String representation of the task meant for saving into the database.
     */
    public String taskInformation() {
        return String.format("%s , %s", this.isDone, this.description);
    }
}