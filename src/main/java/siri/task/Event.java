package siri.task;

/**
 * Represents an Event task in the task list.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Create an Event task with its task description, from and to dates.
     *
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (from: " + from + " to: " + to + ")");
    }

    @Override
    public String toFileString(){
        return "E | " + super.toFileString() + " /from: " + from + " to: " + to;
    }
}
