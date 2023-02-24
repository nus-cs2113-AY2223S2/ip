package duke;
public class Event extends Task {

    /**
     * Creates an Event task.
     * @param description is the task name
     * @param from is the beginning of the event task
     * @param to is the end of the event task
     * @return toString the format of how the task is shown
     */
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to=to;
    }

    @Override
    public String toString() {
        return "[E]"+ super.toString()+ " (from: " + from +"to: " + to + ")";
    }
}

