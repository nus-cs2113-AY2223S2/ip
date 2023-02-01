public class Event extends Task {
    protected String start;
    protected String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    @Override
    public String fullDescription() {
        String full;
        full = (isDone ? "[E][X] " : "[E][ ] ") + this.description +
                "(from: " + this.start + " to: " + this.end + ")";
        return full;
    }
}
