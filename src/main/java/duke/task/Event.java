package duke.task;

public class Event extends Task {
    /** The starting date of the "event" task. */
    protected String from;

    /** The ending date of the "event" task. */
    protected String to;

    /**
     * Creates an duke.task.Event and stores information about the "event" task.
     *
     * @param description The name/description of the "event" task.
     * @param from The start date of the "event" task.
     * @param to The end date of the "event" task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string for output printing, containing information about the task.
     *
     * @return String containing the category label of the task, completion status, description of task
     * and dates (if any).
     */
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "]" + super.getDescription() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Retrieves the start date of the task of type "Event".
     *
     * @return The starting date of the "event" task type.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Retrieves the end date of the task of type "Event".
     *
     * @return The ending date of the "event" task type.
     */
    public String getTo() {
        return to;
    }
}
