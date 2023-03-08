package alltasks;

/**
 * This Event class represents tasks that starts and ends at a specific date and time.
 * Coffee Bot keeps track of the event tasks.
 */
public class Event extends Task{

    private String from;
    private String to;

    public Event(String descriptive, String from, String to) {
        super(descriptive);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "]" + " " + description + "(from: " + this.from + ", to: " + this.to + ")" ;
    }

    @Override
    public String getInfo() {
        return String.format("%s|%s|%s|%s|%s", "Event", this.isDone, this.description, this.from, this.to);
    }
}
