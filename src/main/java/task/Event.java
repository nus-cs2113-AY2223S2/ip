package task;

/**
 * Represent an Event task.
 */
public class Event extends Task {
    private final String start;
    private final String end;

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Get the character representing the type "Event".
     *
     * @return "E"
     */
    public String getTaskType() {
        return "E";
    }

    /**
     * Get the summary of the Event in the format used to display to the user.
     *
     * @return the summary of the Event in UI display format.
     */
    @Override
    public String getSummary() {
        return super.getSummary() + " (from: " + getStart() + " " + "to: " + getEnd() + ")";
    }

    /**
     * Get the summary of the Event in the format used to store in duke.txt.
     *
     * @return the summary of the Event in storage duke.txt format.
     */
    @Override
    public String getDataSummary() {
        return super.getDataSummary() + " | " + getStart() + " | " + getEnd();
    }
}
