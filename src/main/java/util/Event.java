package util;

/**
 * 
 * Represents an event task.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * 
     * Constructs an Event object with the given description, start time and end
     * time.
     * 
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    /**
     * @return A formatted string representation of the Event object for saving to
     *         file.
     */
    @Override
    public String toStringForSave() {
        String doneStatus = super.isDone() ? "1" : "0";
        return String.format("E | %s | %s | %s | %s", doneStatus, super.getDescription(), from, to);
    }
}
