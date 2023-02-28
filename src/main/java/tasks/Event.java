package tasks;

public class Event extends Task {
    /**
     * Constructor method for Event object.
     * @param description This is the description of the Event.
     * @param from This is the startDate of the Event.
     * @param to This is the endDate of the Event.
     * @param type This is the type of the task in which is E stands for E
     */
    protected String from;
    protected String to;

    public Event(String ins, String description, String from, String to) {
        super(ins, description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the details of Event in a specific format.
     * @return String This returns the details of the Event.
     */
    public String toString() {
        return ".[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }
}
