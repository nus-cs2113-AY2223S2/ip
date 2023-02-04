public class Task {
    /***
     * The description of the task.
     */
    protected String description;
    /***
     * The completion status of the task.
     */
    protected boolean isDone;

    /***
     * Functions in the Task class
     * @param description Name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /***
     * Gets the current status of the task within the list.
     * @return isDone.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /***
     * Marks the specified item as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /***
     * Unmarks the specified item as done.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /***
     * The string output when class is called.
     * @return Status and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon()+ "] " + description;
    }
}
