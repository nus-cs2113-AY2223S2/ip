package dude.task;

public class Event extends Task {

    private final String start;
    private final String end;

    /**
     * Constructor for Event class.
     * 
     * @param description Description of the task.
     * @param start Start time of the event.
     * @param end End time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the task.
     * 
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    /**
     * Returns the string representation of the task to be saved.
     * 
     * @return String representation of the task to be saved.
     */
    @Override
    public String toSave() {
        return getStatus() + "=" + "event " + super.getDescription() + " /from " + start + " /to " + end;
    }
}
