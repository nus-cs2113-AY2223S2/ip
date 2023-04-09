/**
 * An abstract class representing a task.
 */
public abstract class Task {

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * A boolean indicating whether the task is done or not.
     */
    protected boolean isDone;

    /**
     * Creates a new task with the given description and sets its isDone status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string indicating whether the task is done or not.
     *
     * @return A string indicating whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the icon representing the type of task.
     *
     * @return The icon representing the type of task.
     */
    public abstract char getTypeIcon();

    /**
     * Marks the given task as done.
     *
     * @param task The task to be marked as done.
     */
    public static void markAsDone(Task task) {
        task.isDone = true;
    }

    /**
     * Marks the given task as not done.
     *
     * @param task The task to be marked as not done.
     */
    public static void markAsNotDone(Task task) {
        task.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return (this.description);
    }
}
