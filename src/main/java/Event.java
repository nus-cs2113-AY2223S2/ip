/**
 * Represents tasks that are labelled as <code>Event</code>.
 */
public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from,String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    /**
     * Add label "[E]" and duration "from" and "to" to a task.
     * @return tasks in event format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
