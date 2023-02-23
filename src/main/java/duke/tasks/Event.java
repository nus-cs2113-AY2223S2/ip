package duke.tasks;

/**
 * The Event Type Task Class
 * */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Creates Event Task
     *
     * @param description The Event Description
     * @param from The Event Start Date/Time
     * @param to The Event End Date/Time
     * */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns The Event Task
     *
     * @return Event Task
     * */
    @Override
    public String toString() {
        return "  [E]" + "[" + getStatusIcon() +"] " + super.getDescription() + "(from " + from + "to " + to + ")";
    }
}