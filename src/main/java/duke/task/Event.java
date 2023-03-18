package duke.task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /* change data to a file format in order to store in a file */
    @Override
    public String toFileFormat() {
        return "E | " + isMarked + " | " + description + " | " + from + " | " + to + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.description + "(from:" + from + "to:" + to + ")";
    }
}
