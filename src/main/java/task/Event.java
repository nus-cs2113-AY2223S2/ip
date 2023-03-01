package task;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description, 'E');
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, 'E', isDone);
        this.from = from;
        this.to = to;
    }


    @Override
    public String getListDescription() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + getDescription() +
                " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toString() {
        return super.toString() + " | " + this.from + " | " + this.to;
    }
}
