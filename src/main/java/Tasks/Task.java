package Tasks;
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     * 
     * @param description Description of the task
     * @param isDone      Status of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task from reading from file to
     * determine if the task is done or not.
     * 
     * @return String the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Getter for the description of the task for user to read.
     * 
     * @return String the description of the task
     */
    public String describeTask() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the description of the task in a format that is
     * suitable for writing to file.
     * 
     * @return String the description of the task in a format that is
     *         suitable for writing to file
     */
    public String describeTaskForFile() {
        return  "";
    }
}
