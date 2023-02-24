package Duke;
public class DukeDeadlines extends DukeTasks{
    protected String by;
    
    /**
     * Creates a new deadline task recorded by Duke with the specified description and due date, and sets its marked status to false. 
     * 
     * @param description Description of the task
     * @param by Deadline of the task
     */
    public DukeDeadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns deadline of the task
     * 
     * @return Deadline of the task
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets deadline of task to this argument
     * 
     * @param by Updated deadline of task
     */
    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return ("D");
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + this.by + ")";
    }
}
