public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        // return "[D]" + super.toString() + " (by: " + by + ")";
        return "[E]" + super.toString() + description + " (from: " + from + " to: " + to + ")";
    }
}
