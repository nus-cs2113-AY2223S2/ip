package duke;

public class Event extends Task {

    protected String by;
    protected String start;
    protected String end;

    /**
     * Creates a new Event object
     *
     * @param description the task description
     * @param start the start time of the event
     * @param end the end time of the event
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the Event object
     */
    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] "+ this.description + " (from:" + this.start + " to:" + this.end + ")";
    }

    /**
     * Returns the string representation of the Event object to be saved in the data
     */
    @Override
    public String saveTask() {
        return ("E" + "//" + checkCompletion() + "//" + getDescription() + "//" + this.start + "//" + this.end + "\n");
    }
}
