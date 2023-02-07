public class Event extends Task {
    protected String start;

    protected String end;

    public Event (String name, boolean isCompleted, String start, String end) {
        super(name, isCompleted);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString () {
        return "[E]" + super.toString() + (" (from: " + start + " to: " + end + ")");
    }
}
