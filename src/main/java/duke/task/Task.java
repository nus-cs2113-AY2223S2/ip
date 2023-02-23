package duke.task;

public abstract class Task {

    protected final String MARKED = "X";
    protected final String UNMARKED = " ";
    protected String description;
    protected String startDate;
    protected String endDate;
    protected boolean isDone;

    /**
     * Base template for Task objects. Consists of a description, startDate, endDate and isDone to keep track
     * whether task is marked as complete.
     *
     * @param description Description of task to be added.
     */
    public Task(String description) {
        this.description = description;
        this.startDate = "";
        this.endDate = "";
        this.isDone = false;
    }

    /**
     * Returns the status icon of a task, for display to the user.
     *
     * @return String Returns 'X' if tasks is marked, otherwise returns ' '.
     */
    public String getStatusIcon() {
        return ((this.isDone) ? MARKED : UNMARKED);
    }

    /**
     * Returns the description of a task, for display to the user.
     *
     * @return String Returns the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void setAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the start date of the task, for display to the user.
     *
     * @return String Returns the start date of the task.
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * Returns the end date of the task, for display to the user.
     *
     * @return String Returns the end date of the task.
     */
    public String getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        String itemStatus = this.getStatusIcon();
        String itemDescription = this.getDescription();
        return String.format("[%s] %s", itemStatus, itemDescription);
    }
}
