package Alex.task;

public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String description, String type, String from, String to) {
        super(description, type);
        this.from = from;
        this.to = to;
    }

    /**
     * Override to update standard printing format for Event task type
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + " to:" + to +")";
    }
    /**
     * get time event starts
     */
    public String getFrom() {
        return from;
    }
    /**
     * get time event ends
     */
    public String getTo() {
        return to;
    }
}
