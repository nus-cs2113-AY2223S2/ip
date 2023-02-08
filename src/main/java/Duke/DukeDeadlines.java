package Duke;
public class DukeDeadlines extends DukeTasks{
    protected String by;
    
    /**
     * Creates a new deadline recorded by Duke with the specified description and due date, and sets its marked status to false. 
     * 
     * @param description Description of the task
     * @param by Deadline of the task
     */
    public DukeDeadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

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
