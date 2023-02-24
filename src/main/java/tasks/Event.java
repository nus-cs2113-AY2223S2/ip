package tasks;

public class Event extends Task {
    /**
     * The start and end details of the event.
     */
    public String start;
    public String end;

    /**
     * Constructs an Event object with the given description, start and end details.
     *
     * @param description the description of the task
     * @param start the start details of the task
     * @param end the end details of the task
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the type of the task: <code>E</code> for event.
     *
     * @return the type of the task, which is <code>E</code>
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns a string representation of the event task, including its type, description, start, and end details.
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "]" + super.toString() + "(" + this.start + "- " + this.end + ")";
    }
}