/**
 * Represents a task which has a specific start and end date/time.
 */
public class Event extends Task {
    String from;
    String to;

    public Event(String description, String from , String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the type of task.
     * @return "E" representing Event task
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns information on when the event starts.
     * @return The start date/time of the task
     */
    @Override
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns information on when the event ends.
     * @return The end date/time of the task
     */
    @Override
    public String getTo() {
        return this.to;
    }

    /**
     * Returns a String representation of the current Event task.
     * @return Current task's type, description, start and end in a string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
