package btb.tasks;

public abstract class Task {

    protected String description;
    private boolean isDone;

    /**
     * Constructs the abstract class and stores the
     * description of task and set task as not done.
     *
     * @param description the description of task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * set the field of isDone
     *
     * @param isDone the variable to indicate status of task
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * converts Task class to a string
     *
     * @return Task class as a string with its
     * corresponding status
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}