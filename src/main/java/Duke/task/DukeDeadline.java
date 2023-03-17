package Duke.task;

/**
 * DukeDeadline class extending DukeTask
 */
public class DukeDeadline extends DukeTask {

    private String by = "";
    
    /**
     * Constructor for DukeDeadline class
     * 
     * @param name String name of the task
     * @param by String date of the deadline
     */
    public DukeDeadline(String name, String by) {
        setName(name);
        setBy(by);
    }

    /**
     * Returns the date of the deadline
     * 
     * @return String date of the deadline
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets the date of the deadline
     * 
     * @param by String date of the deadline
     */
    public void setBy(String by) {
        this.by = by;
    }
    
    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + getBy() + ")";
    }
    
}
