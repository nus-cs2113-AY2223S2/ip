package duke.task;

/**
 * The Event class extends the Task class
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructor for an Event
     * @param description Task description
     * @param from From description
     * @param to To description
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * This method returns the event in the form of a string
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
