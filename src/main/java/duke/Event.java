package duke;

/**
 * Represents an extension of a task with an event.
 * It is a task including the beginning and end timeline of the task.
 */

public class Event extends Task {

    /**
     * Creates an Event task.
     * @param description is the task name
     * @param from is the beginning of the event task
     * @param to is the end of the event task
     */
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to=to;
    }

    /**
     * To display the task in a specific way
     * @return the format of how the task is shown
     */
    @Override
    public String toString() {
        return "[E]"+ super.toString()+ " (from: " + from +"to: " + to + ")";
    }
}

