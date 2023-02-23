package duke;

public class Event extends Task {

    protected String by;
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] "+ this.description + " (from:" + this.start + " to:" + this.end + ")";
    }

    @Override
    public String saveTask() {
        return ("E" + "//" + checkCompletion() + "//" + getDescription() + "//" + this.start + "//" + this.end + "\n");
    }
}
