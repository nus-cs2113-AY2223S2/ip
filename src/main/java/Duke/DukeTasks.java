package Duke;
public class DukeTasks {
    protected String description;
    protected Boolean isDone;

    /**
     * Creates a new task recorded by Duke with the specified description, and sets its marked status to false. 
     * 
     * @param description Description of the task
     */
    public DukeTasks(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" if task is marked, else " "
     * 
     * @return Status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns "T", "D" or "E" depending on type of task constructed
     * 
     * @return Type of task
     */
    public String getTaskType() {
        return (" ");
    }
    
    /** Marks the task */
    public void markDone() {
        this.isDone = true;
    }
    
    /** Unmarks the task */
    public void unmarkDone() {
        this.isDone = false;
    }
    
    @Override
    public String toString() {
        return this.description;
    }
}
