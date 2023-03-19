package nova;

public class Event extends Tasks {
    protected String start;
    protected String end;

    /**
     * Creates a new Event object
     * 
     * @param description the description of the task
     * @param start       the start time of the event
     * @param end         the end time of the event
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string corresponding to the task icon and the start, end time of
     * the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + "from: " + start + "to: " + end + ")";
    }
}
