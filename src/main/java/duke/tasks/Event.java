package duke.tasks;

/**
 * One of the three task type (todo, deadline, event)
 */
public class Event extends Task {
    private String from, to;

    /**
     * @param description task name
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getStart() {
        return this.from;
    }

    public String getEnd() {
        return this.to;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }


}
