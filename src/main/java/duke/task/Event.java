package duke.task;

/**
 * Represents an event task where there is a from and to date
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Instantiates the event task variables
     *
     * @param description description of event
     * @param from start date of event
     * @param to end date of event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from =  from;
        this.to = to;
    }

    /**
     * Instantiates the event task variables
     *
     * @param description description of event
     * @param from start date of event
     * @param to end date of event
     * @param isDone if task is marked as done
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from =  from;
        this.to = to;
    }

    /**
     * Overrides toString method to return output as shown in list of tasks
     *
     * @return string of event in listed format
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
