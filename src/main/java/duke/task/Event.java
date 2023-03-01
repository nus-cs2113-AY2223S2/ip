package duke.task;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String type, String from, String to) {
        super(description, type);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString() + " from: " + this.from + " to: " + this.to;
    }
}