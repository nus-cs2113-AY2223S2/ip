package duke.task;

public class Task {
    /**The description or name of the task added.*/
    protected String description;

    /**Records whether tasking is marked as done or not done.*/
    protected boolean isDone;

    /**
     * Creates a new task by recording the user inputted description and setting the status of the task to undone
     * by default.
     *
     * @param description The name or description of the new task added.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string that represents the "done" or "not done" status of the task in question.
     *
     * @return A string "X" if isDone() is true, " " if isDone() is false.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description or the name of the task in question.
     *
     * @return Name/Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /** Marks the task in question as done by changing the boolean isDone() to true. */
    public void markDone() {
        this.isDone = true;
    }

    /** Marks the task in question as not done by changing the boolean isDone() to false. */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Retrieves the true/false value of the completion status of the task in question.
     *
     * @return The boolean value (1 or 0) of the completion status. Returns 0 if false/uncompleted,
     * 1 if task is completed/true.
     */
    public int getBooleanValueOfStatus() {
        if (isDone) {
            return 1;
        }
        return 0;
    }
}
