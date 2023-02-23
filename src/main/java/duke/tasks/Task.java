package duke.tasks;

/**
 * The Parent Task class
 * */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task
     *
     * @param description The Task Description
     * */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets Task Status
     *
     * @return Task Status
     * */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets Task Description
     *
     * @return Task Description
     * */
    public String getDescription() {
        return description;
    }

    /**
     * Sets Task Status
     *
     * @param status Task Status (True/False)
     * */
    public void setTaskStatus(boolean status) {
        this.isDone = status;
    }
}
