/**
 * Class for an Event Task.
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Constructor for event task
     *
     * @param description name of task.
     * @param startTime time when task should start.
     * @param endTime time when task should end.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Overrides the string to the format of an event task
     *
     * @return a string representing the details of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}