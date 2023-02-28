package duke.tasks;

/**
 * Represents an event Task
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;


    /**
     * Constructor for event task represented with specified type "E"
     *
     * @param description the description of the event task
     * @param startTime        the start time of the event task
     * @param endTime          the end time of the event task
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.type = "[E]";
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Getter function of the start time of event task
     *
     * @return the start time of event task
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Getter function of the end time of event task
     *
     * @return the end time of event task
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Output the event task and its details
     *
     * @return the type, status, description, the start time and the end time of event task
     */
    @Override
    public String toString() {
        return super.toString() + getType() + getStatusIcon() + getDescription()
                + " (from: " + getStartTime() + " to: " + getEndTime() + ")";
    }
}