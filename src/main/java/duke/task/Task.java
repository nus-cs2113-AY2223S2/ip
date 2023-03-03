package duke.task;

import java.io.IOException;

/**
 * <h1>Task</h1>
 * The Task class is the parent task class of the task objects.
 * It contains a description of the task and a boolean status of whether a task has been done or not.
 * <p>
 *
 * @author  Tang Yinxuan (Sophie)
 * @version 1.0
 * @since   2023-03-03
 */
public class Task {
    protected String description;
    protected boolean isDone;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * This method returns the description of the task along with its status
     *
     * @param Nothing
     * @return the String status and description of the task
     */
    public String getDescription() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * This method purely returns the description of the task
     *
     * @param Nothing
     * @return the description of the task
     */
    public String getPureDescription() {
        return this.description;
    }
}
