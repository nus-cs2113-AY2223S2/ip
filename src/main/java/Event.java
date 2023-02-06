public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    public String toString() {
        return super.toString() + "(from: " + from.replace("from ", "") + " to: " + to + ")";
    }
}
