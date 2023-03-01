package Alex.task;

public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;

    /**
     * Constructor for task.
     *
     * @param description description of the task
     * @param type type of task
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * get status of task as a icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    /**
     * Set status of task as done
     */
    public void setAsDone(){
        isDone = true;
    }
    /**
     * get the description of the task
     */
    public String getDescription(){
        return this.description;
    }
    /**
     * Set status of task as not done
     */
    public void setUndone(){
        isDone = false;
    }

    /**
     * get type of task
     */
    public String getType() {
        return type;
    };
    /**
     * check if task status is done
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Standard printing format for a task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

}
