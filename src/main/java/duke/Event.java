package duke;

/**
 * Represents a type of task that starts at a specific date/time and ends
 * at a specific date/time. The start and end date/time are indicated by
 * the user, by the delimiters "/from" and "/to".
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs an Event object that inherits from Task object, assigns the
     * start and end date and/or time of task.
     *
     * @param description The description of the deadline.
     * @param start The start date/time of the deadline.
     * @param end The end date/time of the deadline.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Prints Event object in a specific format.
     *
     * @return A string representing the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start + " to: " + end + ")";
    }
}
