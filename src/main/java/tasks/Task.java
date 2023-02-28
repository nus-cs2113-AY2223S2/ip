package tasks;

/**
 * Represents a task in the tasks list.
 */
public class Task {
   
    public static final String DONE_SYMBOL = "[X] ";
    public static final String UNDONE_SYMBOL = "[ ] ";
    
    protected String description;
    private boolean isDone;
    
    /**
     * Initialises task with a description and boolean indicating if it is done or not done.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    /**
     * returns whether task is done or not done.
     * @return isDone True if done, false if not done.
     */
    public boolean getIsDone() {
        return isDone;
    }
    
    /**
     * Generates and prints representation of whether the task is marked done or undone.
     * Done represented as "[X] ".
     * Undone represented as "[ ] ".
     * @return Representation of task's status.
     */
    public String getStatusIcon() {
        return isDone ? DONE_SYMBOL : UNDONE_SYMBOL;
    }
    
    public void markTask(boolean isDone) {
        this.isDone = isDone;
    }
    
    public String toString() {
        return getStatusIcon() + description;
    }
    
    /**
     * returns the representation of type of task it is.
     * Todo represented as "T".
     * Event represented as "E".
     * Deadline represented as "D".
     * Used in the formatting of the save file.
     * @return Representation of task type.
     */
    public String getTaskType() {
        return "";
    }
    
    /**
     * returns the description of the task.
     * @return Description of task.
     */
    public String getTaskContent() {
        return "";
    }
}
