package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initial constructor for task. Status is set to false (undone) by default.
     *
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Constructor for task when status is provided.
     *
     * @param description task description
     * @param isDone      status of the task. True if done and false if not done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Return status symbol of the task.
     *
     * @return status symbol, "X" if done and whitespace if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    /**
     * Print out the task with all the details.
     *
     * @return Full task in the format of "[status symbol] task description".
     */
    public String printTask() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
