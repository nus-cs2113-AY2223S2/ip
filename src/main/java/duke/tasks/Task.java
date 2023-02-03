package duke.tasks;

/**
 * Manage each task.
 * Private attributes: taskDescription, isDone.
 * Public methods to get/edit task status.
 */
public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Returns the status of a particular task.
     *
     * @return X or ' '
     */
    public String getTaskStatus() {

        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark task as done.
     */
    public void markDone(){

        this.isDone = true;
    }

    /**
     * Mark task as not done.
     */
    public void undo(){

        this.isDone = false;
    }

    @Override
    public String toString(){
        String taskObjectString = "[" + getTaskStatus() + "]" + this.taskDescription;
        return taskObjectString;
    }
}
