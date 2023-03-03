package duke.task;

/**
 * <h1>Event</h1>
 * The Event class is the child of the Task class.
 * It represents the Event tasks.
 * <p>
 *
 * @author  Tang Yinxuan (Sophie)
 * @version 1.0
 * @since   2023-03-03
 */
public class Event extends Task {
    protected String description;
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        setFrom(from);
        setTo(to);
    }
    public String getTo() {
        return this.to;
    }
    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    // Solution below adapted from referring to the getDescription() method by:
    // https://github.com/nichyjt/ip/blob/master/src/main/java/max/task/Event.java
    @Override
    public String getDescription() {
        return "[E] " + super.getDescription() + "(from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }
}
