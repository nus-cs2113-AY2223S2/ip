package tasks;

public class Event extends Task {
    private String from;
    private String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    /** Upon creating the event, the from and to dates will be concatenated to the displayed description.
     * @param description The description of the event.
     * @param from The date this event begins.
     * @param to The date this event ends.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        setFrom(from);
        setTo(to);
        setFormattedDescription(description + '(' + from + to + ')');
    }
}
