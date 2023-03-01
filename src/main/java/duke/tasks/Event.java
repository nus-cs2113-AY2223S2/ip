package duke.tasks;

/**
 * Event class that keeps track of the task object with an additional duration parameter,
 * from and to time parameters.
 */
public class Event extends Task{
    /**
     * String representation of when the event starts.
     */
    protected String from;
    /**
     * String representation of when the event ends.
     */
    protected String to;

    /**
     * Constructs an event task with the given description and duration.
     *
     * @param description Description of the event task.
     * @param from String representation of when the event starts.
     * @param to String representation of when the event ends.
     */
    public Event (String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the task into its string representation which contains
     * information such as the task type, completion status, description, start and end times.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts the event task into a format to be saved in the database.
     *
     * @return String representation of the event task meant for saving into the database.
     */
    @Override
    public String taskInformation() {
        return String.format("%s , %s , %s , %s", "event", super.taskInformation(), this.from, this.to);
    }
}