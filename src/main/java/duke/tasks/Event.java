package duke.tasks;

/**
 * Represents event object that it a type of task
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;


    /**
     * Constructor for event task object, with its specified type "E"
     *
     * @param description the description of the current event
     * @param startTime        the start time of the event
     * @param endTime          the end time of the event
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.type = "[E]";
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets start time of event object
     *
     * @return the start time of event
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Gets end time of event object
     *
     * @return the end time of event
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Prints the event object elements
     *
     * @return the type, status, description, from start time and to end time of current event object
     */
    @Override
    public String toString() {
        return super.toString() + getType() + getStatusIcon() + getDescription()
                + " (from: " + getStartTime() + " to: " + getEndTime() + ")";
    }
}