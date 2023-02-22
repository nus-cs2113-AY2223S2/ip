package duke.tasks;

/**
 * Superclass of all types of tasks.
 * Contains basic attributes of task description and task status(isDone).
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
     * '[X]' as done and '[]' as not done.
     *
     * @return X or ' '
     */
    public String getTaskStatus() {

        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskDescription() {
        return taskDescription;
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

    /**
     * Overwrite toString method to return the task with associated task status prefix.
     *
     * @return
     */
    @Override
    public String toString(){
        String taskObjectString = "[" + getTaskStatus() + "]" + this.taskDescription;
        return taskObjectString;
    }

    public String convertToData(){
        return "";
    }
}
