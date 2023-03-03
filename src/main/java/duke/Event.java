package duke;

/**
 * Event subclass of Task
 */
public class Event extends Deadline {
    public String from;

    /**
     * Constructor for an Event
     * @param description description of the Event
     * @param from starting time of the Event
     * @param to ending time of the Event
     */
    public Event(String description, String from, String to) {
        super(description, to);
        this.from = from;
        this.type = TaskType.EVENT;
    }

    /**
     * Gets a formatted string of the Event with properties
     * @return the formatted string
     */
    @Override
    public String getLabel() {
        String typeIndicator = "[E]";
        String doneIndicator = "[" + (this.isDone ? "X" : " ") + "]";
        String suffix = "(from: " + this.from + " to: " + this.by + ")";
        return typeIndicator + doneIndicator + " " + this.description + " " + suffix;
    }
}
