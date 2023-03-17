package Duke.task;

/**
 * DukeEvent class extending DukeTask
 */
public class DukeEvent extends DukeTask {

    private String from = "";
    private String to = "";
    
    /**
     * Constructor for DukeEvent class
     * 
     * @param name String name of the task
     * @param from String start date of the event
     * @param to String end date of the event
     */
    public DukeEvent(String name, String from, String to) {
        setName(name);
        setFrom(from);
        setTo(to);
    }
    
    /**
     * Returns the end date of the event
     * 
     * @return String date of the event
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the end date of the event
     * 
     * @param to String date of the event
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Returns the start date of the event
     * 
     * @return String date of the event
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the start date of the event
     * 
     * @param from String date of the event
     */
    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String getTaskType() {
        return "E";
    }
    
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }

}
