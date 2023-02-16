package tasks;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String ins, String description, String from, String to) {
        super(ins, description);
        this.from = from;
        this.to = to;
        this.type = 'E';

    }

    public String toString() {
        return ".[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }
}
