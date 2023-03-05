package buddy.tasks;

public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructor for Event class which is a type of Task
     *
     * @param description Description of Event
     * @param from        Start of Event
     * @param to          End of Event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getStart() {
        return this.from;
    }

    public String getEnd() {
        return this.to;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}