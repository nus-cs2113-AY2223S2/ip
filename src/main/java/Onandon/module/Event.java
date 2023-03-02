package Onandon.module;

// Class for the 'event' command.
public class Event extends Task {

    protected String to;
    protected String from;

    public Event(String description, String to, String from) {
        super(description);
        this.to = to;
        this.from = from;
    }

    @Override
    public String getTo() { return to; }

    @Override
    public String getFrom() { return from; }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + description + " (from: " + from + " to: " + to + ")";
    }
}