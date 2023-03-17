package Duke;

/**
 * Superclass of tasks objects created by Duke
 */
public abstract class DukeTask {
    
    protected String name = "";
    protected Boolean isDone = false;
    
    /**
     * Returns the type of task<p>
     * If the task is a ToDo, it will return "T"<p>
     * If the task is a Deadline, it will return "D"<p>
     * If the task is an Event, it will return "E"
     * 
     * @return String type of task
     */
    public abstract String getTaskType();
    
    /**
     * Sets the name of the task
     * 
     * @param name String name of the task
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Marks the task as done
     */
    public void mark() {
        isDone = true;
    }
    
    /**
     * Marks the task as undone
     */
    public void unmark() {
        isDone = false;
    }
    
    /**
     * Returns the status of the task
     * 
     * @return String "X" if task is done, " " if task is not done
     */
    public String getStatus() {
        return (isDone) ? "X" : " ";
    }

    /**
     * Returns the name of the task
     * 
     * @return String name of the task
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + getName();
    }
}
