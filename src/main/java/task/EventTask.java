package task;

public class EventTask extends Task {

    protected String start;
    protected String end;

    public EventTask(String description, String start, String end) {
        super(description);

        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "x" : " ") + "] " + description + " (from: " + start + " to: " + end + ")";
    }
}
