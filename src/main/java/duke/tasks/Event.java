package duke.tasks;

/**
 * Class creates an Event type task.
 */
public class Event extends Task {

    private String fromDate;
    private String toDate;

    /**
     * Creates an Event object and initializes fromDate and toDate.
     *
     * @param description Description of event user wants to add.
     * @param fromDate Start date and time of event.
     * @param toDate End date and time of event.
     * @param isDone Tells if this event has been completed.
     */
    public Event(String description, String fromDate, String toDate, boolean isDone) {
        super(description, isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns the task type of event.
     *
     * @return Task type.
     */
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns the start date and time of event.
     *
     * @return Start time and date.
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * Returns the end date and time of event.
     *
     * @return End time and date.
     */
    public String getToDate() {
        return toDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate + " to: " + toDate + ")" ;
    }
}
