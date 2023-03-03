package duke.tasks;

/**
 * Represents tasks that are classified as a <code>Event</code>
 * These are tasks that have a start and end date
 */
public class Event extends Task {
    protected String start;
    protected String end;
    protected String type = "event";

    /**
     * Creates an object of type <code>Event</code>
     *
     * @param description name of task
     * @param start event's start date
     * @param end event's end date
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public void printTask() {
        System.out.println("[E][" + getStatusIcon() + "] " + description + "(from:" + start + "to:" + end + ")");
    }
}