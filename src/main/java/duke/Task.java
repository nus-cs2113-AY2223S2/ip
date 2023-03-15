package duke;

/**
 * Represents a task to be tracked by the user.
 * a Task is a "todo" by default
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to construct a Task
     * Mark the task as undone by default
     * @param description the details of the todo
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * get the completion status of the task (isDone)
     * @return [X] if the task is done and [ ] otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * gets the icon of this task type
     * @return [T] for task todo
     */
    public String getTypeIcon() {
        return "[T]";
    }

    /**
     * mark the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * mark the task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns all the information about the task
     *
     * @return a String with all the information about the task
     */
    public String toString() {
        return getTypeIcon() + getStatusIcon() + description;
    }

}
