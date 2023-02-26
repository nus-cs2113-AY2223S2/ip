package app.tasks;

/**
 * Class used to represent an Event.
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;


    /**
     * Constructor to create a new Event.
     * @param taskDescription Description of the Event inherited from Task.
     * @param isDone Boolean value indicating if the Event is done or not.
     * @param startTime Starting time of the Event.
     * @param endTime Ending time of the Event.
     */
    public Event(String taskDescription, boolean isDone, String startTime, String endTime) {
        super(taskDescription, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Getter to obtain starting time of an event.
     * @return Starting time of the Event.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Getter to obtain ending time of an event.
     * @return Ending time of the Event
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Method to print an Event in specified format with its given attributes.
     * @return A string representation of an Event.
     */
    @Override
    public String toString() {
        return " [E][" + getStatusIcon() + "] " + taskDescription + " (from: " + startTime + " to: " + endTime + ")";
    }
}
