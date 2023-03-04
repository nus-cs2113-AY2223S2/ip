package duke.tasks;

/**
 * Class creates a Task object.
 */
public class Task {

    private String description;
    private boolean isDone;

    /**
     * Creates a Task object and initializes description and isDone.
     *
     * @param description Description of task user wants to add.
     * @param isDone Tells if this task has been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Sets isDone to true since task is done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Sets isDone to false since task is not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the status of the task (completed or not).
     *
     * @return "X" if task has been completed.
     */
    public String getStatusIcon() {
        // mark a completed task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the type of the task. Method overriden in child classes.
     *
     * @return Empty string.
     */
    public String getTaskType() {
        return "";
    }

    @Override
    public String toString() {
        String[] command =  description.split("/ | :");
        return "[" + getStatusIcon() + "] "  + command[0];
    }

}
