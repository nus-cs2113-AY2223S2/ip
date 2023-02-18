package duke.task;

public class Event extends Task {

    public String from;
    public String to;

    /**
     * Constructor to initialise description, start time and end time
     * of event task.
     *
     * @param description Description of the event.
     * @param from Start date/time of event.
     * @param to End date/time of event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string in the required format.
     *
     * @return Event description, start, end date/time in the required format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
