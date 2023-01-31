public class Event extends Task {
    protected String from;
    protected String by;

    public Event(String description, String from, String by) {
        super(description);
        this.from = from;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] "+ super.toString() + " (" + from + " " + by + ")";
    }
}
