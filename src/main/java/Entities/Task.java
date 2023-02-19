package Entities;

/**
 * Basic Task
 */
public class Task {
    protected String taskDescription;
    protected boolean isDone;

    /**
     * Constructor for Task Class
     * @param taskDescription
     * @param isDone
     */
    public Task(String taskDescription, boolean isDone) {
        setTaskDescription(taskDescription);
        setIsDone(isDone);
    }

    /**
     * Gets whether task is completed
     * @return true if task is completed, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Sets task completion status
     * @param isDone new completion status of task
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Updates task details
     * @param taskDescription new details of task
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Gets the details of tasks
     * @return details of tasks
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Defines how Task should be printed
     * @return string format of Task
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.getTaskDescription();
    }
}
