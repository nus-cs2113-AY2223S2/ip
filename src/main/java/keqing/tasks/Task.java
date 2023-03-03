package keqing.tasks;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected static int taskCount = 0;
    protected boolean isDone = false;

    /**
     * To set the status of a task as finished.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * To set the status of a task as unfinished.
     */
    public void setNotDone() {
        isDone = false;
    }

    /**
     * A constructor.
     */
    public Task() {
    }

    /**
     * A constructor.
     *
     * @param description the content of the task typed in by the user
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Gets the type of tasks.
     *
     * @return the string indicating the type of the task
     */
    public String getTaskType() {
        return null;
    }

    /**
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return the current total number of tasks
     */
    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * To set the total number of tasks
     *
     * @param count the number to be set
     */
    public static void setTaskCount(int count) {
        taskCount = count;
    }

    /**
     *
     * @return a string indicating the detailed information of the task
     */
    public String toString() {
        if(isDone){
            return "[X] " +  description;
        }
        else{
            return "[ ] " +  description;
        }
    }

    /**
     *
     * @return the ArrayList of string containing the details of the deadline/event starting and ending time
     */
    public ArrayList<String> returnAttribute() {
        return null;
    }

    /**
     *
     * @return the current status of the task
     */
    public boolean getStatus() {
        return isDone;
    }
}